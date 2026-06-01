package com.app.fusionarpdfs.domain.repository

import android.net.Uri
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.PdfFileItem

interface PdfMergerRepository {

    suspend fun mergePdfs(
        files: List<PdfFileItem>,
        outputFileName: String,
        outputUri: Uri,
        onProgress: (currentIndex: Int, total: Int) -> Unit = { _, _ -> },
    ): Result<MergeHistoryItem>
}
