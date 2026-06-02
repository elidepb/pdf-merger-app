package com.app.fusionarpdfs.presentation.home

import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.presentation.common.ImportFailuresDialogState

data class HomeUiState(
    val pdfs: List<PdfFileItem> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val importFailuresDialog: ImportFailuresDialogState? = null,
) {
    val isEmpty: Boolean get() = pdfs.isEmpty()
    val canContinue: Boolean get() = pdfs.size >= 2
    val pdfCountLabel: String get() = "${pdfs.size} PDF${if (pdfs.size == 1) "" else "s"} seleccionado${if (pdfs.size == 1) "" else "s"}"
}

sealed interface HomeContinueAction {
    data object NavigateToReorder : HomeContinueAction

    data class ShowMessage(val message: String) : HomeContinueAction
}
