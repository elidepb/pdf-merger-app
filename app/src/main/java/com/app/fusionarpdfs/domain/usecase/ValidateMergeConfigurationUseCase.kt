package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.core.utils.isValidPdfFileName
import com.app.fusionarpdfs.core.utils.normalizePdfFileName
import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.MergeConfigurationError
import com.app.fusionarpdfs.domain.model.MergeConfigurationValidation
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.model.PdfSelectionValidation
import com.app.fusionarpdfs.domain.model.ValidationError
import javax.inject.Inject

class ValidateMergeConfigurationUseCase @Inject constructor(
    private val validatePdfSelectionUseCase: ValidatePdfSelectionUseCase,
) {

    operator fun invoke(
        pdfs: List<PdfFileItem>,
        configuration: MergeConfiguration,
    ): MergeConfigurationValidation {
        when (val selectionValidation = validatePdfSelectionUseCase(pdfs)) {
            is PdfSelectionValidation.Invalid -> {
                return MergeConfigurationValidation.Invalid(
                    error = selectionValidation.error.toMergeConfigurationError(),
                )
            }

            PdfSelectionValidation.Valid -> Unit
        }

        val fileName = configuration.outputFileName.trim()
        if (fileName.isEmpty()) {
            return MergeConfigurationValidation.Invalid(MergeConfigurationError.EMPTY_FILE_NAME)
        }

        if (!isValidPdfFileName(fileName)) {
            return MergeConfigurationValidation.Invalid(MergeConfigurationError.INVALID_FILE_NAME)
        }

        if (configuration.outputUri.isNullOrBlank()) {
            return MergeConfigurationValidation.Invalid(MergeConfigurationError.MISSING_OUTPUT_LOCATION)
        }

        return MergeConfigurationValidation.Valid
    }

    fun normalizedConfiguration(configuration: MergeConfiguration): MergeConfiguration {
        return configuration.copy(
            outputFileName = normalizePdfFileName(configuration.outputFileName),
        )
    }
}

private fun ValidationError.toMergeConfigurationError(): MergeConfigurationError {
    return when (this) {
        ValidationError.TOO_FEW_FILES -> MergeConfigurationError.TOO_FEW_FILES
        ValidationError.DUPLICATE_FILES -> MergeConfigurationError.DUPLICATE_FILES
    }
}
