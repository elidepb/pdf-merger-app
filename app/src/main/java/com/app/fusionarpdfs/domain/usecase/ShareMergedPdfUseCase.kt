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

class ShareMergedPdfUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pdfFileRepository: PdfFileRepository,
) {

    suspend operator fun invoke(uri: Uri, fileName: String): Result<Unit> {
        pdfFileRepository.validatePdfAccessible(uri).fold(
            onSuccess = {},
            onFailure = {
                return Result.failure(PdfAccessException(PdfAccessError.FILE_INACCESSIBLE))
            },
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = MimeTypes.PDF
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, fileName)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        return try {
            context.startActivity(
                Intent.createChooser(intent, AppConstants.SHARE_PDF_CHOOSER_TITLE),
            )
            Result.success(Unit)
        } catch (_: ActivityNotFoundException) {
            Result.failure(PdfAccessException(PdfAccessError.NO_SHARE_APP))
        }
    }
}
