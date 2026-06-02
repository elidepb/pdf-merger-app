package com.app.fusionarpdfs.presentation.progress

import com.app.fusionarpdfs.presentation.common.ErrorDialogState

data class ProgressUiState(
    val isRunning: Boolean = true,
    val isCancelling: Boolean = false,
    val currentStep: Int = 0,
    val totalSteps: Int = 0,
    val currentFileName: String? = null,
    val progressFraction: Float = 0f,
    val statusMessage: String = "Preparando fusión…",
    val completedResultId: String? = null,
    val errorDialog: ErrorDialogState? = null,
)
