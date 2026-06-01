package com.app.fusionarpdfs.presentation.pdf

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.app.fusionarpdfs.core.constants.MimeTypes

class PdfDocumentPickerState(
    val openPicker: () -> Unit,
)

@Composable
fun rememberPdfDocumentPicker(
    onResult: (List<Uri>) -> Unit,
): PdfDocumentPickerState {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
    ) { uris ->
        if (uris.isNotEmpty()) {
            onResult(uris)
        }
    }

    return remember(launcher) {
        PdfDocumentPickerState(
            openPicker = { launcher.launch(arrayOf(MimeTypes.PDF)) },
        )
    }
}
