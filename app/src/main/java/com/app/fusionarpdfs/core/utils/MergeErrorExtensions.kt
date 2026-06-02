package com.app.fusionarpdfs.core.utils

import com.app.fusionarpdfs.domain.model.MergeError

fun MergeError.isRetryable(): Boolean {
    return when (this) {
        MergeError.CANCELLED,
        MergeError.CORRUPT,
        MergeError.PROTECTED,
        MergeError.EMPTY,
        MergeError.INVALID_CONFIGURATION -> false

        MergeError.INACCESSIBLE,
        MergeError.WRITE_FAILED,
        MergeError.INSUFFICIENT_SPACE,
        MergeError.UNKNOWN -> true
    }
}

fun MergeError.dialogTitle(): String {
    return when (this) {
        MergeError.CANCELLED -> "Fusión cancelada"
        else -> "Error al fusionar"
    }
}
