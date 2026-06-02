package com.app.fusionarpdfs.domain.model

enum class PdfAccessError {
    FILE_INACCESSIBLE,
    NO_VIEWER_APP,
    NO_SHARE_APP,
}

class PdfAccessException(
    val error: PdfAccessError,
) : Exception()
