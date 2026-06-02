package com.app.fusionarpdfs.presentation.common.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.ErrorDialogState

@Composable
fun AppErrorDialog(
    state: ErrorDialogState,
    onConfirm: (ErrorDialogAction) -> Unit,
    onDismiss: (ErrorDialogAction) -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            val action = state.dismissAction ?: state.confirmAction
            onDismiss(action)
        },
        title = { Text(state.title) },
        text = { Text(state.message) },
        confirmButton = {
            TextButton(onClick = { onConfirm(state.confirmAction) }) {
                Text(state.confirmText)
            }
        },
        dismissButton = state.dismissText?.let { dismissText ->
            {
                TextButton(
                    onClick = {
                        onDismiss(state.dismissAction ?: ErrorDialogAction.Dismiss)
                    },
                ) {
                    Text(dismissText)
                }
            }
        },
    )
}
