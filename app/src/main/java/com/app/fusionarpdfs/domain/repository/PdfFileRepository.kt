package com.app.fusionarpdfs.domain.repository

import android.net.Uri
import com.app.fusionarpdfs.domain.model.PdfFileItem

interface PdfFileRepository {

    fun persistUriPermissions(uris: List<Uri>)

    fun releaseUriPermission(uri: Uri)

    suspend fun resolvePdfMetadata(uri: Uri, order: Int): Result<PdfFileItem>

    suspend fun validatePdfAccessible(uri: Uri): Result<Unit>

    fun persistOutputUriPermission(uri: Uri)
}
