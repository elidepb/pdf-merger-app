package com.app.fusionarpdfs.presentation.preview

import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.domain.model.PdfFileItem

data class PreviewUiState(
    val pdfs: List<PdfFileItem> = emptyList(),
    val outputFileName: String = AppConstants.DEFAULT_MERGED_FILE_NAME,
    val outputUri: String? = null,
    val outputLocationLabel: String? = null,
    val totalSizeBytes: Long = 0,
) {
    val hasOutputLocation: Boolean get() = !outputUri.isNullOrBlank()
}

sealed interface PreviewMergeAction {
    data object NavigateToProgress : PreviewMergeAction

    data class ShowMessage(val message: String) : PreviewMergeAction
}
