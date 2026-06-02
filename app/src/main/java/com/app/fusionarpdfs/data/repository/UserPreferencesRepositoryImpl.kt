package com.app.fusionarpdfs.data.repository

import com.app.fusionarpdfs.data.preferences.UserPreferencesLocalDataSource
import com.app.fusionarpdfs.domain.model.AppThemeMode
import com.app.fusionarpdfs.domain.model.UserPreferences
import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val localDataSource: UserPreferencesLocalDataSource,
) : UserPreferencesRepository {

    override val preferencesFlow: Flow<UserPreferences> = localDataSource.preferencesFlow

    override suspend fun setThemeMode(themeMode: AppThemeMode) {
        localDataSource.setThemeMode(themeMode)
    }

    override suspend fun setDefaultPdfFileName(fileName: String) {
        localDataSource.setDefaultPdfFileName(fileName)
    }

    override suspend fun setConfirmBeforeMerge(enabled: Boolean) {
        localDataSource.setConfirmBeforeMerge(enabled)
    }
}
