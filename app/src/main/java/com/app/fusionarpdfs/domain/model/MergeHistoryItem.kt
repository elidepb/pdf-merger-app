package com.app.fusionarpdfs.domain.model

data class MergeHistoryItem(
    val id: String,
    val fileName: String,
    val fileUri: String,
    val fileSizeBytes: Long,
    val createdAt: Long,
)
