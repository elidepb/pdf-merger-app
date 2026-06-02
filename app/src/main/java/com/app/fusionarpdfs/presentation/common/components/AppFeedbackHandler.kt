package com.app.fusionarpdfs.presentation.common.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.ErrorDialogState

@Composable
fun AppFeedbackHandler(
    snackbarHostState: SnackbarHostState,
    snackbarMessage: String?,
    onSnackbarShown: () -> Unit,
    errorDialog: ErrorDialogState?,
    onErrorDialogDismiss: () -> Unit,
    onErrorDialogAction: (ErrorDialogAction) -> Unit,
) {
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onSnackbarShown()
        }
    }

    errorDialog?.let { dialogState ->
        AppErrorDialog(
            state = dialogState,
            onConfirm = { action ->
                onErrorDialogDismiss()
                onErrorDialogAction(action)
            },
            onDismiss = { action ->
                onErrorDialogDismiss()
                onErrorDialogAction(action)
            },
        )
    }
}
