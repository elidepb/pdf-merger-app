package com.app.fusionarpdfs.presentation.reorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.core.utils.toUserMessage
import com.app.fusionarpdfs.domain.model.PdfSelectionValidation
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.usecase.RemoveSelectedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.UpdatePdfOrderUseCase
import com.app.fusionarpdfs.domain.usecase.ValidatePdfSelectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ReorderViewModel @Inject constructor(
    mergeSessionRepository: MergeSessionRepository,
    private val updatePdfOrderUseCase: UpdatePdfOrderUseCase,
    private val removeSelectedPdfUseCase: RemoveSelectedPdfUseCase,
    private val validatePdfSelectionUseCase: ValidatePdfSelectionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReorderUiState())
    val uiState: StateFlow<ReorderUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            mergeSessionRepository.selectedPdfs.collect { pdfs ->
                _uiState.update { it.copy(pdfs = pdfs) }
            }
        }
    }

    fun onMove(fromIndex: Int, toIndex: Int) {
        val current = _uiState.value.pdfs.toMutableList()
        if (fromIndex !in current.indices || toIndex !in current.indices) return
        if (fromIndex == toIndex) return

        val item = current.removeAt(fromIndex)
        current.add(toIndex, item)
        updatePdfOrderUseCase(current)
    }

    fun onRemovePdf(pdfId: String) {
        removeSelectedPdfUseCase(pdfId)
    }

    fun onPreviewClick(): ReorderPreviewAction {
        return when (val validation = validatePdfSelectionUseCase(_uiState.value.pdfs)) {
            is PdfSelectionValidation.Valid -> ReorderPreviewAction.NavigateToPreview

            is PdfSelectionValidation.Invalid -> ReorderPreviewAction.ShowMessage(
                validation.error.toUserMessage(),
            )
        }
    }
}
