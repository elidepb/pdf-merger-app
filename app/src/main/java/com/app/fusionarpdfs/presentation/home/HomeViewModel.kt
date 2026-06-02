package com.app.fusionarpdfs.presentation.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.core.utils.toUserMessage
import com.app.fusionarpdfs.domain.model.PdfImportResult
import com.app.fusionarpdfs.domain.model.PdfSelectionValidation
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.usecase.AddPdfsFromUrisUseCase
import com.app.fusionarpdfs.domain.usecase.ClearPdfSelectionUseCase
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.ValidatePdfSelectionUseCase
import com.app.fusionarpdfs.presentation.common.ImportFailureItem
import com.app.fusionarpdfs.presentation.common.ImportFailuresDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    mergeSessionRepository: MergeSessionRepository,
    private val addPdfsFromUrisUseCase: AddPdfsFromUrisUseCase,
    private val removeSelectedPdfUseCase: RemoveSelectedPdfUseCase,
    private val clearPdfSelectionUseCase: ClearPdfSelectionUseCase,
    private val validatePdfSelectionUseCase: ValidatePdfSelectionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            mergeSessionRepository.selectedPdfs.collect { pdfs ->
                _uiState.update { state ->
                    state.copy(pdfs = pdfs)
                }
            }
        }
    }

    fun onPdfsSelected(uris: List<Uri>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = addPdfsFromUrisUseCase(uris)) {
                is PdfImportResult.EmptySelection -> {
                    _uiState.update { it.copy(isLoading = false) }
                }

                is PdfImportResult.Completed -> {
                    val snackbarMessage = buildImportSnackbarMessage(result)
                    val failuresDialog = buildImportFailuresDialog(result)

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            userMessage = snackbarMessage,
                            importFailuresDialog = failuresDialog,
                        )
                    }
                }
            }
        }
    }

    fun onRemovePdf(pdfId: String) {
        removeSelectedPdfUseCase(pdfId)
    }

    fun onClearSelection() {
        clearPdfSelectionUseCase()
        _uiState.update { it.copy(userMessage = "Selección limpiada") }
    }

    fun onContinueClick(): HomeContinueAction {
        return when (val validation = validatePdfSelectionUseCase(_uiState.value.pdfs)) {
            is PdfSelectionValidation.Valid -> HomeContinueAction.NavigateToReorder

            is PdfSelectionValidation.Invalid -> HomeContinueAction.ShowMessage(
                validation.error.toUserMessage(),
            )
        }
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }

    fun onImportFailuresDialogDismissed() {
        _uiState.update { it.copy(importFailuresDialog = null) }
    }

    private fun buildImportSnackbarMessage(result: PdfImportResult.Completed): String? {
        val addedCount = result.added.size
        val failureCount = result.failures.size

        return when {
            addedCount > 0 && failureCount == 0 -> {
                "$addedCount PDF${if (addedCount == 1) "" else "s"} agregado${if (addedCount == 1) "" else "s"}"
            }

            else -> null
        }
    }

    private fun buildImportFailuresDialog(
        result: PdfImportResult.Completed,
    ): ImportFailuresDialogState? {
        if (result.failures.isEmpty()) return null

        val addedCount = result.added.size
        val failureCount = result.failures.size

        val summary = when {
            addedCount == 0 -> "No se pudo importar ningún PDF."
            else -> "$failureCount archivo${if (failureCount == 1) "" else "s"} no se importó${if (failureCount == 1) "" else "ron"}."
        }

        val title = when {
            addedCount == 0 -> "Error al importar"
            else -> "Algunos archivos no se importaron"
        }

        return ImportFailuresDialogState(
            title = title,
            summary = summary,
            failures = result.failures.map { failure ->
                ImportFailureItem(
                    fileName = failure.uri.lastPathSegment ?: "Archivo desconocido",
                    reason = failure.error.toUserMessage(),
                )
            },
        )
    }
}
