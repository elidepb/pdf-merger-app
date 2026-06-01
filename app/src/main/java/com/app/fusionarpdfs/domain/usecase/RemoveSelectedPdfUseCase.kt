package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import javax.inject.Inject

class RemoveSelectedPdfUseCase @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
    private val pdfFileRepository: PdfFileRepository,
) {

    operator fun invoke(pdfId: String) {
        val pdf = mergeSessionRepository.selectedPdfs.value.find { it.id == pdfId } ?: return
        mergeSessionRepository.removePdf(pdfId)
        pdfFileRepository.releaseUriPermission(pdf.uri)
    }
}
