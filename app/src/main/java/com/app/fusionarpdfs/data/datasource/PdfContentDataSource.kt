package com.app.fusionarpdfs.data.datasource

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.app.fusionarpdfs.core.constants.MimeTypes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PdfContentDataSource @Inject constructor(
    private val contentResolver: ContentResolver,
) {

    fun queryDisplayName(uri: Uri): String? {
        return contentResolver.query(
            uri,
            arrayOf(OpenableColumns.DISPLAY_NAME),
            null,
            null,
            null,
        )?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex == -1 || !cursor.moveToFirst()) {
                null
            } else {
                cursor.getString(nameIndex)
            }
        }
    }

    fun querySizeBytes(uri: Uri): Long? {
        return contentResolver.query(
            uri,
            arrayOf(OpenableColumns.SIZE),
            null,
            null,
            null,
        )?.use { cursor ->
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            if (sizeIndex == -1 || !cursor.moveToFirst()) {
                null
            } else {
                cursor.getLong(sizeIndex)
            }
        }
    }

    fun queryMimeType(uri: Uri): String? {
        return contentResolver.getType(uri)
    }

    fun canOpen(uri: Uri): Boolean {
        return contentResolver.openInputStream(uri)?.use { true } ?: false
    }

    fun hasPdfHeader(uri: Uri): Boolean {
        return contentResolver.openInputStream(uri)?.use { input ->
            val header = ByteArray(4)
            input.read(header) == 4 && header.contentEquals(PDF_HEADER)
        } ?: false
    }

    fun isPdfMimeType(uri: Uri): Boolean {
        val mimeType = queryMimeType(uri)
        return mimeType == null || mimeType == MimeTypes.PDF
    }

    private companion object {
        val PDF_HEADER = "%PDF".toByteArray(Charsets.US_ASCII)
    }
}
