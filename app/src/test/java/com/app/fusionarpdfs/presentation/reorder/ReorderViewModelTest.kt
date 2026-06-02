package com.app.fusionarpdfs.presentation.reorder

import com.app.fusionarpdfs.data.repository.MergeSessionRepositoryImpl
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.UpdatePdfOrderUseCase
import com.app.fusionarpdfs.domain.usecase.ValidatePdfSelectionUseCase
import com.app.fusionarpdfs.testutil.MainDispatcherRule
import com.app.fusionarpdfs.testutil.TestData
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ReorderViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val sessionRepository = MergeSessionRepositoryImpl()
    private val updatePdfOrderUseCase: UpdatePdfOrderUseCase = mockk(relaxed = true)
    private val removeSelectedPdfUseCase: RemoveSelectedPdfUseCase = mockk(relaxed = true)
    private val validatePdfSelectionUseCase = ValidatePdfSelectionUseCase()

    private lateinit var viewModel: ReorderViewModel

    @Before
    fun setUp() {
        viewModel = ReorderViewModel(
            mergeSessionRepository = sessionRepository,
            updatePdfOrderUseCase = updatePdfOrderUseCase,
            removeSelectedPdfUseCase = removeSelectedPdfUseCase,
            validatePdfSelectionUseCase = validatePdfSelectionUseCase,
        )
    }

    @Test
    fun uiState_reflectsSelectedPdfsFromSession() = runTest {
        val pdfs = TestData.pdfItems(3)
        sessionRepository.setSelectedPdfs(pdfs)

        assertEquals(pdfs, viewModel.uiState.value.pdfs)
    }

    @Test
    fun onMove_updatesOrderThroughUseCase() {
        val pdfs = TestData.pdfItems(3)
        sessionRepository.setSelectedPdfs(pdfs)

        viewModel.onMove(fromIndex = 0, toIndex = 2)

        verify(exactly = 1) { updatePdfOrderUseCase(any()) }
    }

    @Test
    fun onMove_ignoresInvalidIndices() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))

        viewModel.onMove(fromIndex = 0, toIndex = 10)
        viewModel.onMove(fromIndex = -1, toIndex = 0)

        verify(exactly = 0) { updatePdfOrderUseCase(any()) }
    }

    @Test
    fun onRemovePdf_delegatesToUseCase() {
        viewModel.onRemovePdf("pdf-1")

        verify(exactly = 1) { removeSelectedPdfUseCase("pdf-1") }
    }

    @Test
    fun onPreviewClick_navigatesWhenSelectionIsValid() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))

        val action = viewModel.onPreviewClick()

        assertEquals(ReorderPreviewAction.NavigateToPreview, action)
    }

    @Test
    fun onPreviewClick_showsMessageWhenSelectionIsInvalid() {
        sessionRepository.setSelectedPdfs(listOf(TestData.pdfItem()))

        val action = viewModel.onPreviewClick()

        assertTrue(action is ReorderPreviewAction.ShowMessage)
        assertEquals(
            "Selecciona al menos 2 PDFs para continuar",
            (action as ReorderPreviewAction.ShowMessage).message,
        )
    }
}
