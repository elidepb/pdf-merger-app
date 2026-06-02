package com.app.fusionarpdfs.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.core.constants.PreferenceKeys
import com.app.fusionarpdfs.core.constants.fusionarPdfsDataStore
import com.app.fusionarpdfs.domain.model.AppThemeMode
import com.app.fusionarpdfs.domain.model.UserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class UserPreferencesLocalDataSource @Inject constructor(
    @ApplicationContext context: Context,
) {

    private val dataStore = context.fusionarPdfsDataStore

    private val themeModeKey = stringPreferencesKey(PreferenceKeys.THEME_MODE)
    private val defaultPdfFileNameKey = stringPreferencesKey(PreferenceKeys.DEFAULT_PDF_FILE_NAME)
    private val confirmBeforeMergeKey = booleanPreferencesKey(PreferenceKeys.CONFIRM_BEFORE_MERGE)

    val preferencesFlow: Flow<UserPreferences> = dataStore.data.map { preferences ->
        UserPreferences(
            themeMode = preferences[themeModeKey]?.toAppThemeMode() ?: AppThemeMode.SYSTEM,
            defaultPdfFileName = preferences[defaultPdfFileNameKey]
                ?: AppConstants.DEFAULT_MERGED_FILE_NAME,
            confirmBeforeMerge = preferences[confirmBeforeMergeKey] ?: true,
        )
    }

    suspend fun setThemeMode(themeMode: AppThemeMode) {
        dataStore.edit { preferences ->
            preferences[themeModeKey] = themeMode.name
        }
    }

    suspend fun setDefaultPdfFileName(fileName: String) {
        dataStore.edit { preferences ->
            preferences[defaultPdfFileNameKey] = fileName
        }
    }

    suspend fun setConfirmBeforeMerge(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[confirmBeforeMergeKey] = enabled
        }
    }
}

private fun String.toAppThemeMode(): AppThemeMode {
    return AppThemeMode.entries.firstOrNull { it.name == this } ?: AppThemeMode.SYSTEM
}
