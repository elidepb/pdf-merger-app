package com.app.fusionarpdfs.domain.usecase

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.core.constants.MimeTypes
import com.app.fusionarpdfs.domain.model.PdfAccessError
import com.app.fusionarpdfs.domain.model.PdfAccessException
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OpenMergedPdfUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pdfFileRepository: PdfFileRepository,
) {

    suspend operator fun invoke(uri: Uri): Result<Unit> {
        pdfFileRepository.validatePdfAccessible(uri).fold(
            onSuccess = {},
            onFailure = {
                return Result.failure(PdfAccessException(PdfAccessError.FILE_INACCESSIBLE))
            },
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, MimeTypes.PDF)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        return try {
            context.startActivity(
                Intent.createChooser(intent, AppConstants.OPEN_PDF_CHOOSER_TITLE),
            )
            Result.success(Unit)
        } catch (_: ActivityNotFoundException) {
            Result.failure(PdfAccessException(PdfAccessError.NO_VIEWER_APP))
        }
    }
}
