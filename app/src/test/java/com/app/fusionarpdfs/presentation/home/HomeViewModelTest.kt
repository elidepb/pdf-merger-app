package com.app.fusionarpdfs.presentation.home

import android.net.Uri
import com.app.fusionarpdfs.data.repository.MergeSessionRepositoryImpl
import com.app.fusionarpdfs.domain.model.PdfFileError
import com.app.fusionarpdfs.domain.model.PdfImportFailure
import com.app.fusionarpdfs.domain.model.PdfImportResult
import com.app.fusionarpdfs.domain.usecase.AddPdfsFromUrisUseCase
import com.app.fusionarpdfs.domain.usecase.ClearPdfSelectionUseCase
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.ValidatePdfSelectionUseCase
import com.app.fusionarpdfs.testutil.MainDispatcherRule
import com.app.fusionarpdfs.testutil.TestData
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val sessionRepository = MergeSessionRepositoryImpl()
    private val addPdfsFromUrisUseCase: AddPdfsFromUrisUseCase = mockk()
    private val removeSelectedPdfUseCase: RemoveSelectedPdfUseCase = mockk(relaxed = true)
    private val clearPdfSelectionUseCase: ClearPdfSelectionUseCase = mockk(relaxed = true)
    private val validatePdfSelectionUseCase = ValidatePdfSelectionUseCase()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            mergeSessionRepository = sessionRepository,
            addPdfsFromUrisUseCase = addPdfsFromUrisUseCase,
            removeSelectedPdfUseCase = removeSelectedPdfUseCase,
            clearPdfSelectionUseCase = clearPdfSelectionUseCase,
            validatePdfSelectionUseCase = validatePdfSelectionUseCase,
        )
    }

    @Test
    fun uiState_reflectsSelectedPdfsFromSession() = runTest {
        val pdfs = TestData.pdfItems(2)
        sessionRepository.setSelectedPdfs(pdfs)

        assertEquals(pdfs, viewModel.uiState.value.pdfs)
    }

    @Test
    fun onContinueClick_navigatesWhenSelectionIsValid() {
        sessionRepository.setSelectedPdfs(TestData.pdfItems(2))

        val action = viewModel.onContinueClick()

        assertEquals(HomeContinueAction.NavigateToReorder, action)
    }

    @Test
    fun onContinueClick_showsMessageWhenTooFewPdfs() {
        sessionRepository.setSelectedPdfs(listOf(TestData.pdfItem()))

        val action = viewModel.onContinueClick()

        assertTrue(action is HomeContinueAction.ShowMessage)
        assertEquals(
            "Selecciona al menos 2 PDFs para continuar",
            (action as HomeContinueAction.ShowMessage).message,
        )
    }

    @Test
    fun onClearSelection_setsUserMessage() {
        viewModel.onClearSelection()

        assertEquals("Selección limpiada", viewModel.uiState.value.userMessage)
        verify(exactly = 1) { clearPdfSelectionUseCase() }
    }

    @Test
    fun onPdfsSelected_showsSnackbarWhenAllImportsSucceed() = runTest {
        val added = TestData.pdfItems(2)
        coEvery { addPdfsFromUrisUseCase(any()) } returns PdfImportResult.Completed(
            added = added,
            failures = emptyList(),
        )

        viewModel.onPdfsSelected(listOf(Uri.parse("content://test/a.pdf")))

        assertEquals("2 PDFs agregados", viewModel.uiState.value.userMessage)
        assertNull(viewModel.uiState.value.importFailuresDialog)
        assertEquals(false, viewModel.uiState.value.isLoading)
    }

    @Test
    fun onPdfsSelected_showsFailuresDialogWhenSomeImportsFail() = runTest {
        val added = listOf(TestData.pdfItem())
        val failureUri = Uri.parse("content://test/invalid.pdf")
        coEvery { addPdfsFromUrisUseCase(any()) } returns PdfImportResult.Completed(
            added = added,
            failures = listOf(
                PdfImportFailure(
                    uri = failureUri,
                    error = PdfFileError.INVALID_FORMAT,
                ),
            ),
        )

        viewModel.onPdfsSelected(listOf(failureUri))

        assertNull(viewModel.uiState.value.userMessage)
        assertEquals("Algunos archivos no se importaron", viewModel.uiState.value.importFailuresDialog?.title)
        assertEquals(1, viewModel.uiState.value.importFailuresDialog?.failures?.size)
    }

    @Test
    fun onUserMessageShown_clearsMessage() {
        viewModel.onClearSelection()
        viewModel.onUserMessageShown()

        assertNull(viewModel.uiState.value.userMessage)
    }
}
