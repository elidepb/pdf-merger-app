package com.app.fusionarpdfs.presentation.progress

data class ProgressUiState(
    val isRunning: Boolean = true,
    val isCancelling: Boolean = false,
    val currentStep: Int = 0,
    val totalSteps: Int = 0,
    val currentFileName: String? = null,
    val progressFraction: Float = 0f,
    val statusMessage: String = "Preparando fusión…",
    val completedResultId: String? = null,
    val errorMessage: String? = null,
)
