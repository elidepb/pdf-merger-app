package com.app.fusionarpdfs.domain.usecase

import android.net.Uri
import com.app.fusionarpdfs.domain.model.MergeConfigurationValidation
import com.app.fusionarpdfs.domain.model.MergeError
import com.app.fusionarpdfs.domain.model.MergeException
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.MergeConfigurationError
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import javax.inject.Inject

class ExecuteMergeFromSessionUseCase @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
    private val validateMergeConfigurationUseCase: ValidateMergeConfigurationUseCase,
    private val mergePdfUseCase: MergePdfUseCase,
) {

    suspend operator fun invoke(
        onProgress: (currentIndex: Int, total: Int) -> Unit = { _, _ -> },
    ): Result<MergeHistoryItem> {
        val pdfs = mergeSessionRepository.selectedPdfs.value
        val configuration = mergeSessionRepository.mergeConfiguration.value
            ?: return Result.failure(MergeException(MergeError.INVALID_CONFIGURATION))

        val normalizedConfiguration = validateMergeConfigurationUseCase.normalizedConfiguration(
            configuration,
        )

        return when (
            val validation = validateMergeConfigurationUseCase(
                pdfs = pdfs,
                configuration = normalizedConfiguration,
            )
        ) {
            MergeConfigurationValidation.Valid -> {
                mergePdfUseCase(
                    files = pdfs,
                    outputFileName = normalizedConfiguration.outputFileName,
                    outputUri = Uri.parse(normalizedConfiguration.outputUri!!),
                    onProgress = onProgress,
                )
            }

            is MergeConfigurationValidation.Invalid -> {
                Result.failure(
                    MergeException(validation.error.toMergeError()),
                )
            }
        }
    }
}

private fun MergeConfigurationError.toMergeError(): MergeError {
    return when (this) {
        MergeConfigurationError.TOO_FEW_FILES -> MergeError.EMPTY
        MergeConfigurationError.DUPLICATE_FILES -> MergeError.INVALID_CONFIGURATION
        MergeConfigurationError.EMPTY_FILE_NAME -> MergeError.INVALID_CONFIGURATION
        MergeConfigurationError.INVALID_FILE_NAME -> MergeError.INVALID_CONFIGURATION
        MergeConfigurationError.MISSING_OUTPUT_LOCATION -> MergeError.INVALID_CONFIGURATION
    }
}
