package com.app.fusionarpdfs.presentation.common.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.fusionarpdfs.presentation.common.ImportFailuresDialogState

@Composable
fun ImportFailuresDialog(
    state: ImportFailuresDialogState,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(state.title) },
        text = {
            LazyColumn(modifier = Modifier.heightIn(max = 280.dp)) {
                item {
                    Text(
                        text = state.summary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 12.dp),
                    )
                }
                items(state.failures) { failure ->
                    Text(
                        text = failure.fileName,
                        style = MaterialTheme.typography.titleSmall,
                    )
                    Text(
                        text = failure.reason,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 12.dp),
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Entendido")
            }
        },
    )
}
