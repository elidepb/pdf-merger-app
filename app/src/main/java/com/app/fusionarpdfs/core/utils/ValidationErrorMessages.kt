package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.ValidationError

fun ValidationError.toUserMessage(): String {
    return when (this) {
        ValidationError.TOO_FEW_FILES -> "Selecciona al menos 2 PDFs para continuar"
        ValidationError.DUPLICATE_FILES -> "Hay archivos duplicados en la selección"
    }
}
