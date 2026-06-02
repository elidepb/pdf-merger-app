package com.app.fusionarpdfs.data.repository

import com.app.fusionarpdfs.data.preferences.MergeHistoryLocalDataSource
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class MergeHistoryRepositoryImpl @Inject constructor(
    private val mergeHistoryLocalDataSource: MergeHistoryLocalDataSource,
) : MergeHistoryRepository {

    override fun observeHistory(): Flow<List<MergeHistoryItem>> {
        return mergeHistoryLocalDataSource.historyFlow
    }

    override suspend fun getById(id: String): MergeHistoryItem? {
        return mergeHistoryLocalDataSource.readHistory().firstOrNull { it.id == id }
    }

    override suspend fun save(item: MergeHistoryItem): Result<Unit> {
        return runCatching {
            val current = mergeHistoryLocalDataSource.readHistory()
            val updated = listOf(item) + current.filterNot { it.id == item.id }
            mergeHistoryLocalDataSource.writeHistory(updated)
        }
    }

    override suspend fun delete(id: String): Result<Unit> {
        return runCatching {
            val updated = mergeHistoryLocalDataSource.readHistory().filterNot { it.id == id }
            mergeHistoryLocalDataSource.writeHistory(updated)
        }
    }

    override suspend fun clearAll(): Result<Unit> {
        return runCatching {
            mergeHistoryLocalDataSource.writeHistory(emptyList())
        }
    }
}
