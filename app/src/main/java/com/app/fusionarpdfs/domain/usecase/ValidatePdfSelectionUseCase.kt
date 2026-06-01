package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.model.PdfSelectionValidation
import com.app.fusionarpdfs.domain.model.ValidationError
import javax.inject.Inject

class ValidatePdfSelectionUseCase @Inject constructor() {

    operator fun invoke(files: List<PdfFileItem>): PdfSelectionValidation {
        if (files.size < AppConstants.MIN_PDF_COUNT_TO_MERGE) {
            return PdfSelectionValidation.Invalid(ValidationError.TOO_FEW_FILES)
        }

        val uniqueUris = files.map { it.uri.toString() }.toSet()
        if (uniqueUris.size != files.size) {
            return PdfSelectionValidation.Invalid(ValidationError.DUPLICATE_FILES)
        }

        return PdfSelectionValidation.Valid
    }
}
