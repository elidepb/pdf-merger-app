package com.app.fusionarpdfs.presentation.history

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.presentation.common.ErrorDialogState

data class HistoryUiState(
    val items: List<MergeHistoryItem> = emptyList(),
    val showClearConfirmation: Boolean = false,
    val userMessage: String? = null,
    val errorDialog: ErrorDialogState? = null,
) {
    val isEmpty: Boolean get() = items.isEmpty()
}
