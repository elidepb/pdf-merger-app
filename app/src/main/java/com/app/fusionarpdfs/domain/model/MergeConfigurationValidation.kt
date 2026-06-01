package com.app.fusionarpdfs.domain.model

sealed class MergeConfigurationValidation {
    data object Valid : MergeConfigurationValidation()

    data class Invalid(
        val error: MergeConfigurationError,
    ) : MergeConfigurationValidation()
}

enum class MergeConfigurationError {
    TOO_FEW_FILES,
    DUPLICATE_FILES,
    EMPTY_FILE_NAME,
    INVALID_FILE_NAME,
    MISSING_OUTPUT_LOCATION,
}
