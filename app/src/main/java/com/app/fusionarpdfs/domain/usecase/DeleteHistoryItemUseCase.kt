package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import javax.inject.Inject

class DeleteHistoryItemUseCase @Inject constructor(
    private val mergeHistoryRepository: MergeHistoryRepository,
) {

    suspend operator fun invoke(id: String): Result<Unit> {
        return mergeHistoryRepository.delete(id)
    }
}
