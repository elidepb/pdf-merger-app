package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.MergeConfigurationError

fun MergeConfigurationError.toUserMessage(): String {
    return when (this) {
        MergeConfigurationError.TOO_FEW_FILES -> "Selecciona al menos 2 PDFs para continuar"
        MergeConfigurationError.DUPLICATE_FILES -> "Hay archivos duplicados en la selección"
        MergeConfigurationError.EMPTY_FILE_NAME -> "Ingresa un nombre para el archivo"
        MergeConfigurationError.INVALID_FILE_NAME -> "El nombre del archivo no es válido"
        MergeConfigurationError.MISSING_OUTPUT_LOCATION -> "Selecciona dónde guardar el PDF"
    }
}
