package com.app.fusionarpdfs.domain.model

import com.app.fusionarpdfs.core.constants.AppConstants

data class UserPreferences(
    val themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    val defaultPdfFileName: String = AppConstants.DEFAULT_MERGED_FILE_NAME,
    val confirmBeforeMerge: Boolean = true,
)
