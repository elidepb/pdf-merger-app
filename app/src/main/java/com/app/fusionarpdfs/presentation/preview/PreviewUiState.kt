package com.app.fusionarpdfs.presentation.preview

import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.presentation.common.ErrorDialogState

data class PreviewUiState(
    val pdfs: List<PdfFileItem> = emptyList(),
    val outputFileName: String = AppConstants.DEFAULT_MERGED_FILE_NAME,
    val outputUri: String? = null,
    val outputLocationLabel: String? = null,
    val totalSizeBytes: Long = 0,
    val confirmBeforeMerge: Boolean = true,
    val showMergeConfirmation: Boolean = false,
    val pendingConfiguration: MergeConfiguration? = null,
    val errorDialog: ErrorDialogState? = null,
) {
    val hasOutputLocation: Boolean get() = !outputUri.isNullOrBlank()
}

sealed interface PreviewMergeAction {
    data object NavigateToProgress : PreviewMergeAction

    data object None : PreviewMergeAction

    data class ShowMessage(val message: String) : PreviewMergeAction
}
