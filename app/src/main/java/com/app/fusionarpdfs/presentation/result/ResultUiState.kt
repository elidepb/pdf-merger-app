package com.app.fusionarpdfs.presentation.result

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.presentation.common.ErrorDialogState

data class ResultUiState(
    val isLoading: Boolean = true,
    val mergeResult: MergeHistoryItem? = null,
    val isFileAccessible: Boolean = true,
    val userMessage: String? = null,
    val errorDialog: ErrorDialogState? = null,
)

sealed interface ResultAction {
    data object NavigateToHome : ResultAction
}
