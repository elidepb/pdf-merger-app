package com.app.fusionarpdfs.domain.model

import android.net.Uri

data class PdfFileItem(
    val id: String,
    val uri: Uri,
    val name: String,
    val sizeBytes: Long,
    val order: Int,
)
