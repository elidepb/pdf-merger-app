package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import javax.inject.Inject

class ClearPdfSelectionUseCase @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
    private val pdfFileRepository: PdfFileRepository,
) {

    operator fun invoke() {
        mergeSessionRepository.selectedPdfs.value.forEach { pdf ->
            pdfFileRepository.releaseUriPermission(pdf.uri)
        }
        mergeSessionRepository.clearSelection()
    }
}
