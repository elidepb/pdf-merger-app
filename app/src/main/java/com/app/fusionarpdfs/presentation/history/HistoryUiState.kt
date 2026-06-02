package com.app.fusionarpdfs.presentation.history

import com.app.fusionarpdfs.domain.model.MergeHistoryItem

data class HistoryUiState(
    val items: List<MergeHistoryItem> = emptyList(),
    val showClearConfirmation: Boolean = false,
    val userMessage: String? = null,
) {
    val isEmpty: Boolean get() = items.isEmpty()
}
