package com.app.fusionarpdfs.core.constants

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.fusionarPdfsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "fusionarpdfs_preferences",
)

object PreferenceKeys {
    const val MERGE_HISTORY_JSON = "merge_history_json"
    const val THEME_MODE = "theme_mode"
    const val DEFAULT_PDF_FILE_NAME = "default_pdf_file_name"
    const val CONFIRM_BEFORE_MERGE = "confirm_before_merge"
}
