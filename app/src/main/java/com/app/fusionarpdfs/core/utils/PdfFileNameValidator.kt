package com.app.fusionarpdfs.core.utils

private val INVALID_FILE_NAME_CHARS = Regex("[\\\\/:*?\"<>|]")

fun normalizePdfFileName(fileName: String): String {
    val trimmed = fileName.trim()
    if (trimmed.isEmpty()) return trimmed
    return if (trimmed.lowercase().endsWith(".pdf")) {
        trimmed
    } else {
        "$trimmed.pdf"
    }
}

fun isValidPdfFileName(fileName: String): Boolean {
    val normalized = normalizePdfFileName(fileName)
    if (normalized.isBlank()) return false
    if (normalized.length > 255) return false
    val nameWithoutExtension = normalized.removeSuffix(".pdf").removeSuffix(".PDF")
    if (nameWithoutExtension.isBlank()) return false
    return !INVALID_FILE_NAME_CHARS.containsMatchIn(normalized)
}
