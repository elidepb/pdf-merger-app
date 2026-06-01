package com.app.fusionarpdfs.domain.model

data class MergeConfiguration(
    val outputFileName: String,
    val outputUri: String? = null,
)
