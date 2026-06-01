package com.app.fusionarpdfs.data.repository

import android.net.Uri
import com.app.fusionarpdfs.data.datasource.PdfContentDataSource
import com.app.fusionarpdfs.data.datasource.UriPermissionDataSource
import com.app.fusionarpdfs.domain.model.PdfFileError
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.model.PdfValidationException
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PdfFileRepositoryImpl @Inject constructor(
    private val pdfContentDataSource: PdfContentDataSource,
    private val uriPermissionDataSource: UriPermissionDataSource,
) : PdfFileRepository {

    override fun persistUriPermissions(uris: List<Uri>) {
        uriPermissionDataSource.persistReadPermissions(uris)
    }

    override fun releaseUriPermission(uri: Uri) {
        uriPermissionDataSource.releaseReadPermission(uri)
    }

    override suspend fun resolvePdfMetadata(uri: Uri, order: Int): Result<PdfFileItem> {
        return withContext(Dispatchers.IO) {
            validatePdfAccessible(uri).map {
                val name = pdfContentDataSource.queryDisplayName(uri)
                    ?.takeIf { it.isNotBlank() }
                    ?: DEFAULT_FILE_NAME
                val sizeBytes = pdfContentDataSource.querySizeBytes(uri) ?: 0L

                PdfFileItem(
                    id = UUID.randomUUID().toString(),
                    uri = uri,
                    name = name,
                    sizeBytes = sizeBytes,
                    order = order,
                )
            }
        }
    }

    override suspend fun validatePdfAccessible(uri: Uri): Result<Unit> {
        return withContext(Dispatchers.IO) {
            if (!pdfContentDataSource.canOpen(uri)) {
                return@withContext Result.failure(PdfValidationException(PdfFileError.INACCESSIBLE))
            }

            if (!pdfContentDataSource.isPdfMimeType(uri)) {
                return@withContext Result.failure(PdfValidationException(PdfFileError.INVALID_FORMAT))
            }

            val sizeBytes = pdfContentDataSource.querySizeBytes(uri)
            if (sizeBytes != null && sizeBytes <= 0L) {
                return@withContext Result.failure(PdfValidationException(PdfFileError.EMPTY))
            }

            if (!pdfContentDataSource.hasPdfHeader(uri)) {
                return@withContext Result.failure(PdfValidationException(PdfFileError.INVALID_FORMAT))
            }

            Result.success(Unit)
        }
    }

    private companion object {
        const val DEFAULT_FILE_NAME = "documento.pdf"
    }
}
