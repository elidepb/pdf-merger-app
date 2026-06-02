package com.app.fusionarpdfs.testutil

import android.net.Uri
import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.PdfFileItem
import io.mockk.every
import io.mockk.mockk

object TestData {

    fun pdfUri(name: String = "documento.pdf"): Uri {
        return Uri.parse("content://test/$name")
    }

    fun pdfItem(
        id: String = "pdf-1",
        uri: Uri = pdfUri("documento-$id.pdf"),
        name: String = "documento-$id.pdf",
        sizeBytes: Long = 1024L,
        order: Int = 0,
    ): PdfFileItem {
        return PdfFileItem(
            id = id,
            uri = uri,
            name = name,
            sizeBytes = sizeBytes,
            order = order,
        )
    }

    fun pdfItems(count: Int): List<PdfFileItem> {
        return (1..count).map { index ->
            pdfItem(
                id = "pdf-$index",
                uri = pdfUri("doc-$index.pdf"),
                name = "doc-$index.pdf",
                order = index - 1,
            )
        }
    }

    fun mergeHistoryItem(
        id: String = "history-1",
        fileName: String = "fusionado.pdf",
        fileUri: String = "content://test/fusionado.pdf",
        fileSizeBytes: Long = 2048L,
        createdAt: Long = 1_700_000_000_000L,
    ): MergeHistoryItem {
        return MergeHistoryItem(
            id = id,
            fileName = fileName,
            fileUri = fileUri,
            fileSizeBytes = fileSizeBytes,
            createdAt = createdAt,
        )
    }

    fun mergeConfiguration(
        outputFileName: String = "fusionado.pdf",
        outputUri: String? = "content://test/output/fusionado.pdf",
    ): MergeConfiguration {
        return MergeConfiguration(
            outputFileName = outputFileName,
            outputUri = outputUri,
        )
    }

    fun mockUri(uriString: String, lastSegment: String? = null): Uri {
        val uri = mockk<Uri>()
        every { uri.toString() } returns uriString
        every { uri.lastPathSegment } returns (lastSegment ?: uriString.substringAfterLast('/'))
        return uri
    }
}
