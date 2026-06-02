package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import javax.inject.Inject

class ClearHistoryUseCase @Inject constructor(
    private val mergeHistoryRepository: MergeHistoryRepository,
) {

    suspend operator fun invoke(): Result<Unit> {
        return mergeHistoryRepository.clearAll()
    }
}
