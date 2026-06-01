package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.MergeError

fun MergeError.toUserMessage(): String {
    return when (this) {
        MergeError.INACCESSIBLE -> "No se pudo acceder a uno de los archivos"
        MergeError.CORRUPT -> "Uno de los PDFs está dañado o no es válido"
        MergeError.PROTECTED -> "Uno de los PDFs está protegido con contraseña"
        MergeError.EMPTY -> "Uno de los PDFs está vacío o no hay archivos para fusionar"
        MergeError.WRITE_FAILED -> "No se pudo guardar el PDF resultante"
        MergeError.INSUFFICIENT_SPACE -> "No hay espacio suficiente para guardar el archivo"
        MergeError.INVALID_CONFIGURATION -> "La configuración de fusión no es válida"
        MergeError.CANCELLED -> "La fusión fue cancelada"
        MergeError.UNKNOWN -> "Ocurrió un error inesperado al fusionar"
    }
}
