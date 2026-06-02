package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.PdfAccessError

fun PdfAccessError.toUserMessage(): String {
    return when (this) {
        PdfAccessError.FILE_INACCESSIBLE -> "El archivo ya no está disponible. Puede haber sido movido o eliminado"
        PdfAccessError.NO_VIEWER_APP -> "No hay una aplicación disponible para abrir PDFs"
        PdfAccessError.NO_SHARE_APP -> "No hay una aplicación disponible para compartir"
    }
}
