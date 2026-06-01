package com.app.fusionarpdfs.domain.model

sealed class PdfSelectionValidation {
    data object Valid : PdfSelectionValidation()

    data class Invalid(
        val error: ValidationError,
    ) : PdfSelectionValidation()
}

enum class ValidationError {
    TOO_FEW_FILES,
    DUPLICATE_FILES,
}
