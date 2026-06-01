package com.app.fusionarpdfs.data.repository

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MergeHistoryRepositoryImpl @Inject constructor() : MergeHistoryRepository {

    private val history = MutableStateFlow<List<MergeHistoryItem>>(emptyList())

    override fun observeHistory(): Flow<List<MergeHistoryItem>> = history.asStateFlow()

    override suspend fun getById(id: String): MergeHistoryItem? {
        return history.value.firstOrNull { it.id == id }
    }

    override suspend fun save(item: MergeHistoryItem): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Persistencia de historial disponible en PR-10"),
        )
    }

    override suspend fun delete(id: String): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Persistencia de historial disponible en PR-10"),
        )
    }

    override suspend fun clearAll(): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Persistencia de historial disponible en PR-10"),
        )
    }
}
