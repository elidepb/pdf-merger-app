package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import javax.inject.Inject

class UpdatePdfOrderUseCase @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
) {

    operator fun invoke(pdfs: List<PdfFileItem>) {
        mergeSessionRepository.updatePdfOrder(pdfs)
    }
}
