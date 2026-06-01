package com.app.fusionarpdfs.domain.usecase

import android.net.Uri
import com.app.fusionarpdfs.domain.model.MergeError
import com.app.fusionarpdfs.domain.model.MergeException
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.PdfMergerRepository
import javax.inject.Inject

class MergePdfUseCase @Inject constructor(
    private val pdfMergerRepository: PdfMergerRepository,
) {

    suspend operator fun invoke(
        files: List<PdfFileItem>,
        outputFileName: String,
        outputUri: Uri,
        onProgress: (currentIndex: Int, total: Int) -> Unit = { _, _ -> },
    ): Result<MergeHistoryItem> {
        if (files.isEmpty()) {
            return Result.failure(MergeException(MergeError.EMPTY))
        }

        if (outputFileName.isBlank()) {
            return Result.failure(MergeException(MergeError.INVALID_CONFIGURATION))
        }

        return pdfMergerRepository.mergePdfs(
            files = files.sortedBy { it.order },
            outputFileName = outputFileName,
            outputUri = outputUri,
            onProgress = onProgress,
        )
    }
}
