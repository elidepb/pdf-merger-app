package com.app.fusionarpdfs.domain.model

import android.net.Uri

enum class PdfFileError {
    INACCESSIBLE,
    EMPTY,
    INVALID_FORMAT,
    DUPLICATE,
}

data class PdfImportFailure(
    val uri: Uri,
    val error: PdfFileError,
)

sealed class PdfImportResult {
    data object EmptySelection : PdfImportResult()

    data class Completed(
        val added: List<PdfFileItem>,
        val failures: List<PdfImportFailure>,
    ) : PdfImportResult()
}
