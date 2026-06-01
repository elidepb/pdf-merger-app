package com.app.fusionarpdfs.data.datasource

import com.app.fusionarpdfs.domain.model.MergeError
import com.app.fusionarpdfs.domain.model.MergeException
import com.tom_roush.pdfbox.pdmodel.encryption.InvalidPasswordException
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MergeErrorMapper @Inject constructor() {

    fun toMergeException(throwable: Throwable): MergeException {
        if (throwable is MergeException) return throwable

        return when (throwable) {
            is InvalidPasswordException -> MergeException(MergeError.PROTECTED, throwable)

            is IOException -> mapIOException(throwable)

            else -> MergeException(MergeError.UNKNOWN, throwable)
        }
    }

    private fun mapIOException(error: IOException): MergeException {
        val message = error.message?.lowercase().orEmpty()
        return when {
            message.contains("enospc") || message.contains("no space") -> {
                MergeException(MergeError.INSUFFICIENT_SPACE, error)
            }

            message.contains("permission") || message.contains("eacces") -> {
                MergeException(MergeError.WRITE_FAILED, error)
            }

            message.contains("password") || message.contains("encrypted") -> {
                MergeException(MergeError.PROTECTED, error)
            }

            message.contains("premature eof") ||
                message.contains("invalid") ||
                message.contains("corrupt") ||
                message.contains("malformed") -> {
                MergeException(MergeError.CORRUPT, error)
            }

            else -> MergeException(MergeError.CORRUPT, error)
        }
    }
}
