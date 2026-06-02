package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.PdfAccessError
import com.app.fusionarpdfs.domain.model.PdfAccessException

fun Throwable.toAccessErrorMessage(): String {
    return when (this) {
        is PdfAccessException -> error.toUserMessage()
        else -> PdfAccessError.FILE_INACCESSIBLE.toUserMessage()
    }
}
