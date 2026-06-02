package com.app.fusionarpdfs.domain.usecase

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.core.constants.MimeTypes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OpenMergedPdfUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    operator fun invoke(uri: Uri): Result<Unit> {
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
            Result.failure(OpenPdfException)
        }
    }
}

object OpenPdfException : Exception()
