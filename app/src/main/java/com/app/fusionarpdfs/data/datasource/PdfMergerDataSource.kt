package com.app.fusionarpdfs.data.datasource

import android.content.ContentResolver
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.provider.OpenableColumns
import com.app.fusionarpdfs.domain.model.MergeError
import com.app.fusionarpdfs.domain.model.MergeException
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.tom_roush.pdfbox.io.MemoryUsageSetting
import com.tom_roush.pdfbox.multipdf.PDFMergerUtility
import com.tom_roush.pdfbox.pdmodel.PDDocument
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PdfMergerDataSource @Inject constructor(
    private val contentResolver: ContentResolver,
    private val mergeErrorMapper: MergeErrorMapper,
) {

    fun mergePdfs(
        files: List<PdfFileItem>,
        outputUri: Uri,
        onProgress: (currentIndex: Int, total: Int) -> Unit,
    ): Long {
        if (files.isEmpty()) {
            throw MergeException(MergeError.EMPTY)
        }

        val sortedFiles = files.sortedBy { it.order }
        val merger = PDFMergerUtility()
        val total = sortedFiles.size
        val inputStreams = mutableListOf<InputStream>()

        try {
            sortedFiles.forEachIndexed { index, file ->
                onProgress(index, total)
                validateSourcePdf(file)
                val inputStream = openInputStream(file.uri)
                    ?: throw MergeException(MergeError.INACCESSIBLE)
                inputStreams.add(inputStream)
                merger.addSource(inputStream)
            }

            val outputStream = contentResolver.openOutputStream(outputUri, "wt")
                ?: throw MergeException(MergeError.WRITE_FAILED)

            outputStream.use { stream ->
                merger.setDestinationStream(stream)
                merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly())
            }
        } catch (throwable: Throwable) {
            throw mergeErrorMapper.toMergeException(throwable)
        } finally {
            inputStreams.forEach { stream ->
                try {
                    stream.close()
                } catch (_: IOException) {
                }
            }
        }

        onProgress(total, total)

        return querySizeBytes(outputUri)
            ?: queryFileDescriptorSize(outputUri)
            ?: 0L
    }

    private fun validateSourcePdf(file: PdfFileItem) {
        val inputStream = openInputStream(file.uri)
            ?: throw MergeException(MergeError.INACCESSIBLE)

        inputStream.use { stream ->
            try {
                PDDocument.load(stream).use { document ->
                    if (document.numberOfPages <= 0) {
                        throw MergeException(MergeError.EMPTY)
                    }
                }
            } catch (throwable: Throwable) {
                throw mergeErrorMapper.toMergeException(throwable)
            }
        }
    }

    private fun openInputStream(uri: Uri): InputStream? {
        return try {
            contentResolver.openInputStream(uri)
        } catch (_: SecurityException) {
            null
        } catch (_: IOException) {
            null
        }
    }

    private fun querySizeBytes(uri: Uri): Long? {
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

    private fun queryFileDescriptorSize(uri: Uri): Long? {
        return try {
            contentResolver.openFileDescriptor(uri, "r")?.use(ParcelFileDescriptor::getStatSize)
        } catch (_: Exception) {
            null
        }
    }
}
