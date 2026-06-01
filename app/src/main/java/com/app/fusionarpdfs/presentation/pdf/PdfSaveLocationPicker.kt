package com.app.fusionarpdfs.presentation.pdf

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.app.fusionarpdfs.core.constants.MimeTypes

class PdfSaveLocationPickerState(
    val openPicker: (suggestedFileName: String) -> Unit,
)

@Composable
fun rememberPdfSaveLocationPicker(
    onResult: (Uri) -> Unit,
): PdfSaveLocationPickerState {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument(MimeTypes.PDF),
    ) { uri ->
        if (uri != null) {
            onResult(uri)
        }
    }

    return remember(launcher) {
        PdfSaveLocationPickerState(
            openPicker = { suggestedFileName ->
                launcher.launch(suggestedFileName)
            },
        )
    }
}
