package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.PdfFileError

fun PdfFileError.toUserMessage(): String {
    return when (this) {
        PdfFileError.INACCESSIBLE -> "No se pudo acceder al archivo"
        PdfFileError.EMPTY -> "El PDF está vacío"
        PdfFileError.INVALID_FORMAT -> "El archivo no es un PDF válido"
        PdfFileError.DUPLICATE -> "Este PDF ya está en la selección"
    }
}
