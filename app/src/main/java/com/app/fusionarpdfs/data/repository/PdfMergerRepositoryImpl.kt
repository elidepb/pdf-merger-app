package com.app.fusionarpdfs.data.repository

import android.net.Uri
import com.app.fusionarpdfs.data.datasource.MergeErrorMapper
import com.app.fusionarpdfs.data.datasource.PdfMergerDataSource
import com.app.fusionarpdfs.domain.model.MergeException
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.PdfMergerRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class PdfMergerRepositoryImpl @Inject constructor(
    private val pdfMergerDataSource: PdfMergerDataSource,
    private val mergeErrorMapper: MergeErrorMapper,
) : PdfMergerRepository {

    override suspend fun mergePdfs(
        files: List<PdfFileItem>,
        outputFileName: String,
        outputUri: Uri,
        onProgress: (currentIndex: Int, total: Int) -> Unit,
    ): Result<MergeHistoryItem> {
        return withContext(Dispatchers.IO) {
            try {
                val outputSizeBytes = pdfMergerDataSource.mergePdfs(
                    files = files,
                    outputUri = outputUri,
                    onProgress = onProgress,
                )

                Result.success(
                    MergeHistoryItem(
                        id = UUID.randomUUID().toString(),
                        fileName = outputFileName,
                        fileUri = outputUri.toString(),
                        fileSizeBytes = outputSizeBytes,
                        createdAt = System.currentTimeMillis(),
                    ),
                )
            } catch (exception: MergeException) {
                Result.failure(exception)
            } catch (throwable: Throwable) {
                Result.failure(mergeErrorMapper.toMergeException(throwable))
            }
        }
    }
}
