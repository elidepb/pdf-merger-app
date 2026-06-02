package com.app.fusionarpdfs.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import com.app.fusionarpdfs.core.accessibility.A11yLabels
import com.app.fusionarpdfs.core.theme.Spacing
import com.app.fusionarpdfs.core.utils.formatFileSize
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.presentation.common.components.FusionarPdfsCard
import com.app.fusionarpdfs.presentation.common.components.PdfFileIcon

@Composable
fun HomePdfListItem(
    pdf: PdfFileItem,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FusionarPdfsCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.lg, vertical = Spacing.md)
                .semantics(mergeDescendants = true) {
                    contentDescription = "${pdf.name}, ${formatFileSize(pdf.sizeBytes)}"
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PdfFileIcon()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Spacing.md),
            ) {
                Text(
                    text = pdf.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = formatFileSize(pdf.sizeBytes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "${A11yLabels.DELETE} ${pdf.name}",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
