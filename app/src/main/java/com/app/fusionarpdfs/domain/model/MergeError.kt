package com.app.fusionarpdfs.domain.model

enum class MergeError {
    INACCESSIBLE,
    CORRUPT,
    PROTECTED,
    EMPTY,
    WRITE_FAILED,
    INSUFFICIENT_SPACE,
    INVALID_CONFIGURATION,
    CANCELLED,
    UNKNOWN,
}

class MergeException(
    val error: MergeError,
    cause: Throwable? = null,
) : Exception(cause)
