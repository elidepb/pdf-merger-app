package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import javax.inject.Inject

class GetMergeResultUseCase @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
    private val mergeHistoryRepository: MergeHistoryRepository,
) {

    suspend operator fun invoke(resultId: String?): MergeHistoryItem? {
        val sessionResult = mergeSessionRepository.lastMergeResult.value
        if (resultId.isNullOrBlank()) {
            return sessionResult
        }

        if (sessionResult?.id == resultId) {
            return sessionResult
        }

        return mergeHistoryRepository.getById(resultId)
    }
}
