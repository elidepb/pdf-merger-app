package com.app.fusionarpdfs.presentation.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.fusionarpdfs.core.accessibility.A11yLabels

@Composable
fun PdfFileIcon(
    modifier: Modifier = Modifier,
    decorative: Boolean = true,
) {
    Icon(
        imageVector = Icons.Default.PictureAsPdf,
        contentDescription = if (decorative) null else A11yLabels.PDF_FILE,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}
