package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import javax.inject.Inject

class StartNewMergeUseCase @Inject constructor(
    private val clearPdfSelectionUseCase: ClearPdfSelectionUseCase,
    private val mergeSessionRepository: MergeSessionRepository,
) {

    operator fun invoke() {
        clearPdfSelectionUseCase()
        mergeSessionRepository.clearSession()
    }
}
