package com.app.fusionarpdfs.domain.usecase

import android.net.Uri
import com.app.fusionarpdfs.domain.model.PdfValidationException
import com.app.fusionarpdfs.domain.model.PdfFileError
import com.app.fusionarpdfs.domain.model.PdfImportFailure
import com.app.fusionarpdfs.domain.model.PdfImportResult
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import javax.inject.Inject

class AddPdfsFromUrisUseCase @Inject constructor(
    private val pdfFileRepository: PdfFileRepository,
    private val mergeSessionRepository: MergeSessionRepository,
) {

    suspend operator fun invoke(uris: List<Uri>): PdfImportResult {
        if (uris.isEmpty()) {
            return PdfImportResult.EmptySelection
        }

        pdfFileRepository.persistUriPermissions(uris)

        val existingUris = mergeSessionRepository.selectedPdfs.value
            .map { it.uri.toString() }
            .toSet()

        val processedUris = existingUris.toMutableSet()
        val added = mutableListOf<PdfFileItem>()
        val failures = mutableListOf<PdfImportFailure>()
        val startOrder = mergeSessionRepository.selectedPdfs.value.size

        uris.forEach { uri ->
            if (uri.toString() in processedUris) {
                failures.add(PdfImportFailure(uri, PdfFileError.DUPLICATE))
                return@forEach
            }

            val result = pdfFileRepository.resolvePdfMetadata(
                uri = uri,
                order = startOrder + added.size,
            )

            result.fold(
                onSuccess = { pdf ->
                    added.add(pdf)
                    processedUris.add(uri.toString())
                },
                onFailure = { error ->
                    failures.add(
                        PdfImportFailure(
                            uri = uri,
                            error = mapError(error),
                        ),
                    )
                },
            )
        }

        if (added.isNotEmpty()) {
            mergeSessionRepository.addPdfs(added)
        }

        return PdfImportResult.Completed(
            added = added,
            failures = failures,
        )
    }

    private fun mapError(throwable: Throwable): PdfFileError {
        return when (throwable) {
            is PdfValidationException -> throwable.error
            else -> PdfFileError.INACCESSIBLE
        }
    }
}
