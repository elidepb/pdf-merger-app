package com.app.fusionarpdfs.data.repository

import android.net.Uri
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PdfFileRepositoryImpl @Inject constructor() : PdfFileRepository {

    override suspend fun resolvePdfMetadata(uri: Uri, order: Int): Result<PdfFileItem> {
        return Result.failure(
            UnsupportedOperationException("Selección de PDFs disponible en PR-03"),
        )
    }

    override suspend fun validatePdfAccessible(uri: Uri): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Validación de PDFs disponible en PR-03"),
        )
    }
}
