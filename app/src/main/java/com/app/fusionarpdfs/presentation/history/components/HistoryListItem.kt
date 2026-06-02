package com.app.fusionarpdfs.presentation.history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
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
import com.app.fusionarpdfs.core.utils.formatMergeDate
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.presentation.common.components.FusionarPdfsCard
import com.app.fusionarpdfs.presentation.common.components.PdfFileIcon

@Composable
fun HistoryListItem(
    item: MergeHistoryItem,
    onItemClick: () -> Unit,
    onOpen: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FusionarPdfsCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onItemClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.md, vertical = Spacing.md)
                .semantics(mergeDescendants = true) {
                    contentDescription = "${item.fileName}, ${formatFileSize(item.fileSizeBytes)}, ${formatMergeDate(item.createdAt)}"
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
                    text = item.fileName,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${formatFileSize(item.fileSizeBytes)} · ${formatMergeDate(item.createdAt)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            IconButton(onClick = onOpen) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = "${A11yLabels.OPEN_PDF}: ${item.fileName}",
                )
            }
            IconButton(onClick = onShare) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "${A11yLabels.SHARE_PDF}: ${item.fileName}",
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "${A11yLabels.DELETE} ${item.fileName}",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
