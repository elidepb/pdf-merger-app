package com.app.fusionarpdfs.presentation.common

enum class ErrorDialogAction {
    Dismiss,
    NavigateBack,
    NavigateHome,
    Retry,
}

data class ErrorDialogState(
    val title: String,
    val message: String,
    val confirmText: String = "Entendido",
    val dismissText: String? = null,
    val confirmAction: ErrorDialogAction = ErrorDialogAction.Dismiss,
    val dismissAction: ErrorDialogAction? = null,
)

data class ImportFailureItem(
    val fileName: String,
    val reason: String,
)

data class ImportFailuresDialogState(
    val title: String,
    val summary: String,
    val failures: List<ImportFailureItem>,
)
