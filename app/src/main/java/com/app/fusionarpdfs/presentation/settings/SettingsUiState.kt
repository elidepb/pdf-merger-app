package com.app.fusionarpdfs.presentation.settings

import com.app.fusionarpdfs.domain.model.AppThemeMode

data class SettingsUiState(
    val themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    val defaultPdfFileName: String = "",
    val confirmBeforeMerge: Boolean = true,
    val appVersion: String = "",
    val showClearHistoryConfirmation: Boolean = false,
    val userMessage: String? = null,
)
