package com.app.fusionarpdfs.domain.repository

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import kotlinx.coroutines.flow.Flow

interface MergeHistoryRepository {

    fun observeHistory(): Flow<List<MergeHistoryItem>>

    suspend fun getById(id: String): MergeHistoryItem?

    suspend fun save(item: MergeHistoryItem): Result<Unit>

    suspend fun delete(id: String): Result<Unit>

    suspend fun clearAll(): Result<Unit>
}
