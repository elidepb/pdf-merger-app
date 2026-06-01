package com.app.fusionarpdfs.presentation.reorder

import com.app.fusionarpdfs.domain.model.PdfFileItem

data class ReorderUiState(
    val pdfs: List<PdfFileItem> = emptyList(),
) {
    val canContinue: Boolean get() = pdfs.size >= 2
}

sealed interface ReorderPreviewAction {
    data object NavigateToPreview : ReorderPreviewAction

    data class ShowMessage(val message: String) : ReorderPreviewAction
}
