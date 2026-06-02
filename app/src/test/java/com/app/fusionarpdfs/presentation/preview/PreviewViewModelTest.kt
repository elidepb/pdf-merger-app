package com.app.fusionarpdfs.presentation.preview

import android.net.Uri
import com.app.fusionarpdfs.data.repository.MergeSessionRepositoryImpl
import com.app.fusionarpdfs.domain.model.MergeConfigurationError
import com.app.fusionarpdfs.domain.model.MergeConfigurationValidation
import com.app.fusionarpdfs.domain.model.UserPreferences
import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository
import com.app.fusionarpdfs.domain.usecase.PersistOutputUriPermissionUseCase
import com.app.fusionarpdfs.domain.usecase.SaveMergeConfigurationUseCase
import com.app.fusionarpdfs.domain.usecase.ValidateMergeConfigurationUseCase
import com.app.fusionarpdfs.testutil.MainDispatcherRule
import com.app.fusionarpdfs.testutil.TestData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PreviewViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val sessionRepository = MergeSessionRepositoryImpl()
    private val preferencesState = MutableStateFlow(UserPreferences())
    private val userPreferencesRepository: UserPreferencesRepository = mockk {
        every { preferencesFlow } returns preferencesState
    }
    private val validateMergeConfigurationUseCase: ValidateMergeConfigurationUseCase = mockk()
    private val saveMergeConfigurationUseCase: SaveMergeConfigurationUseCase = mockk(relaxed = true)
    private val persistOutputUriPermissionUseCase: PersistOutputUriPermissionUseCase = mockk(relaxed = true)

    private lateinit var viewModel: PreviewViewModel

    @Before
    fun setUp() {
        preferencesState.value = UserPreferences()
        viewModel = createViewModel()
    }

    private fun createViewModel(): PreviewViewModel {
        return PreviewViewModel(
            mergeSessionRepository = sessionRepository,
            userPreferencesRepository = userPreferencesRepository,
            validateMergeConfigurationUseCase = validateMergeConfigurationUseCase,
            saveMergeConfigurationUseCase = saveMergeConfigurationUseCase,
            persistOutputUriPermissionUseCase = persistOutputUriPermissionUseCase,
        )
    }

    @Test
    fun uiState_reflectsSelectedPdfsAndDefaultFileName() = runTest {
        preferencesState.value = UserPreferences(defaultPdfFileName = "mi_fusion.pdf")
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))
        viewModel = createViewModel()

        val state = viewModel.uiState.value
        assertEquals(TestData.pdfItems(2), state.pdfs)
        assertEquals(TestData.pdfItems(2).sumOf { it.sizeBytes }, state.totalSizeBytes)
        assertEquals("mi_fusion.pdf", state.outputFileName)
    }

    @Test
    fun onFileNameChange_updatesOutputFileName() {
        viewModel.onFileNameChange("nuevo_nombre.pdf")

        assertEquals("nuevo_nombre.pdf", viewModel.uiState.value.outputFileName)
    }

    @Test
    fun onOutputLocationSelected_persistsPermissionAndUpdatesState() {
        val uri = Uri.parse("content://test/output/resultado.pdf")

        viewModel.onOutputLocationSelected(uri)

        verify(exactly = 1) { persistOutputUriPermissionUseCase(uri) }
        assertEquals(uri.toString(), viewModel.uiState.value.outputUri)
        assertEquals("resultado.pdf", viewModel.uiState.value.outputLocationLabel)
    }

    @Test
    fun onMergeClick_showsConfirmationWhenConfiguredAndConfirmationEnabled() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))
        viewModel.onFileNameChange("salida.pdf")
        viewModel.onOutputLocationSelected(Uri.parse("content://test/output/salida.pdf"))

        val configuration = TestData.mergeConfiguration()
        every { validateMergeConfigurationUseCase.normalizedConfiguration(any()) } returns configuration
        every {
            validateMergeConfigurationUseCase(
                pdfs = any(),
                configuration = configuration,
            )
        } returns MergeConfigurationValidation.Valid

        val action = viewModel.onMergeClick()

        assertEquals(PreviewMergeAction.None, action)
        assertTrue(viewModel.uiState.value.showMergeConfirmation)
        assertEquals(configuration, viewModel.uiState.value.pendingConfiguration)
    }

    @Test
    fun onMergeClick_navigatesDirectlyWhenConfirmationDisabled() {
        preferencesState.value = UserPreferences(confirmBeforeMerge = false)
        viewModel = createViewModel()
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))

        val configuration = TestData.mergeConfiguration()
        every { validateMergeConfigurationUseCase.normalizedConfiguration(any()) } returns configuration
        every {
            validateMergeConfigurationUseCase(
                pdfs = any(),
                configuration = configuration,
            )
        } returns MergeConfigurationValidation.Valid

        val action = viewModel.onMergeClick()

        assertEquals(PreviewMergeAction.NavigateToProgress, action)
        assertFalse(viewModel.uiState.value.showMergeConfirmation)
        verify(exactly = 1) { saveMergeConfigurationUseCase(configuration) }
    }

    @Test
    fun onMergeClick_showsErrorDialogWhenConfigurationIsInvalid() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))

        val configuration = TestData.mergeConfiguration(outputUri = null)
        every { validateMergeConfigurationUseCase.normalizedConfiguration(any()) } returns configuration
        every {
            validateMergeConfigurationUseCase(
                pdfs = any(),
                configuration = configuration,
            )
        } returns MergeConfigurationValidation.Invalid(MergeConfigurationError.MISSING_OUTPUT_LOCATION)

        val action = viewModel.onMergeClick()

        assertEquals(PreviewMergeAction.None, action)
        assertNotNull(viewModel.uiState.value.errorDialog)
        assertEquals(
            "Selecciona dónde guardar el PDF",
            viewModel.uiState.value.errorDialog?.message,
        )
    }

    @Test
    fun onConfirmMerge_navigatesAndClearsPendingConfiguration() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))
        val configuration = TestData.mergeConfiguration()

        every { validateMergeConfigurationUseCase.normalizedConfiguration(any()) } returns configuration
        every {
            validateMergeConfigurationUseCase(
                pdfs = any(),
                configuration = configuration,
            )
        } returns MergeConfigurationValidation.Valid

        viewModel.onMergeClick()
        val action = viewModel.onConfirmMerge()

        assertEquals(PreviewMergeAction.NavigateToProgress, action)
        assertNull(viewModel.uiState.value.pendingConfiguration)
        assertFalse(viewModel.uiState.value.showMergeConfirmation)
        verify(exactly = 1) { saveMergeConfigurationUseCase(configuration) }
    }

    @Test
    fun onDismissMergeConfirmation_clearsDialogState() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))
        val configuration = TestData.mergeConfiguration()

        every { validateMergeConfigurationUseCase.normalizedConfiguration(any()) } returns configuration
        every {
            validateMergeConfigurationUseCase(
                pdfs = any(),
                configuration = configuration,
            )
        } returns MergeConfigurationValidation.Valid

        viewModel.onMergeClick()
        viewModel.onDismissMergeConfirmation()

        assertFalse(viewModel.uiState.value.showMergeConfirmation)
        assertNull(viewModel.uiState.value.pendingConfiguration)
    }
}
