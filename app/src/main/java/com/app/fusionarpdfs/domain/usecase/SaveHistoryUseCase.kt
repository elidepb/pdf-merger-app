package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(
    private val mergeHistoryRepository: MergeHistoryRepository,
) {

    suspend operator fun invoke(item: MergeHistoryItem): Result<Unit> {
        return mergeHistoryRepository.save(item)
    }
}
