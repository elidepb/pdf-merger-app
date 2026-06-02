package com.app.fusionarpdfs.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.fusionarpdfs.core.constants.PreferenceKeys
import com.app.fusionarpdfs.core.constants.fusionarPdfsDataStore
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@Singleton
class MergeHistoryLocalDataSource @Inject constructor(
    @ApplicationContext context: Context,
) {

    private val dataStore = context.fusionarPdfsDataStore
    private val historyKey = stringPreferencesKey(PreferenceKeys.MERGE_HISTORY_JSON)

    val historyFlow: Flow<List<MergeHistoryItem>> = dataStore.data.map { preferences ->
        MergeHistoryJsonMapper.decode(preferences[historyKey].orEmpty())
    }

    suspend fun readHistory(): List<MergeHistoryItem> {
        return historyFlow.first()
    }

    suspend fun writeHistory(items: List<MergeHistoryItem>) {
        dataStore.edit { preferences ->
            preferences[historyKey] = MergeHistoryJsonMapper.encode(items)
        }
    }
}
