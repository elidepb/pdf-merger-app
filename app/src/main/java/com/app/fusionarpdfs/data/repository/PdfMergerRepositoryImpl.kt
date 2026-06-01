package com.app.fusionarpdfs.data.repository

import android.net.Uri
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.PdfMergerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PdfMergerRepositoryImpl @Inject constructor() : PdfMergerRepository {

    override suspend fun mergePdfs(
        files: List<PdfFileItem>,
        outputFileName: String,
        outputUri: Uri,
        onProgress: (currentIndex: Int, total: Int) -> Unit,
    ): Result<MergeHistoryItem> {
        return Result.failure(
            UnsupportedOperationException("Fusión de PDFs disponible en PR-07"),
        )
    }
}
