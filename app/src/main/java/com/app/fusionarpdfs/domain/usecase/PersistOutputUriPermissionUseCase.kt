package com.app.fusionarpdfs.domain.usecase

import android.net.Uri
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import javax.inject.Inject

class PersistOutputUriPermissionUseCase @Inject constructor(
    private val pdfFileRepository: PdfFileRepository,
) {

    operator fun invoke(uri: Uri) {
        pdfFileRepository.persistOutputUriPermission(uri)
    }
}
