package com.app.fusionarpdfs.domain.repository

import com.app.fusionarpdfs.domain.model.AppThemeMode
import com.app.fusionarpdfs.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    val preferencesFlow: Flow<UserPreferences>

    suspend fun setThemeMode(themeMode: AppThemeMode)

    suspend fun setDefaultPdfFileName(fileName: String)

    suspend fun setConfirmBeforeMerge(enabled: Boolean)
}
