package com.app.fusionarpdfs.presentation.result

import com.app.fusionarpdfs.domain.model.MergeHistoryItem

data class ResultUiState(
    val mergeResult: MergeHistoryItem? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val userMessage: String? = null,
)

sealed interface ResultAction {
    data object NavigateToHome : ResultAction
}
