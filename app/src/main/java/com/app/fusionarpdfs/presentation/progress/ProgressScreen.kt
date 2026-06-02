package com.app.fusionarpdfs.presentation.progress

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.components.AppFeedbackHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    onNavigateToResult: (resultId: String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ProgressViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    BackHandler(enabled = uiState.isRunning) {
    }

    LaunchedEffect(uiState.completedResultId) {
        uiState.completedResultId?.let { resultId ->
            onNavigateToResult(resultId)
            viewModel.onNavigationHandled()
        }
    }

    AppFeedbackHandler(
        snackbarHostState = snackbarHostState,
        snackbarMessage = null,
        onSnackbarShown = {},
        errorDialog = uiState.errorDialog,
        onErrorDialogDismiss = viewModel::onErrorDialogDismissed,
        onErrorDialogAction = { action ->
            when (action) {
                ErrorDialogAction.Retry -> viewModel.startMerge()
                ErrorDialogAction.NavigateBack -> onNavigateBack()
                else -> Unit
            }
        },
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fusionando…") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (uiState.isRunning) {
                CircularProgressIndicator()
            }

            Text(
                text = uiState.statusMessage,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp),
            )

            if (uiState.totalSteps > 0) {
                LinearProgressIndicator(
                    progress = { uiState.progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                )

                Text(
                    text = "${uiState.currentStep} de ${uiState.totalSteps} documentos",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }

            uiState.currentFileName?.let { fileName ->
                Text(
                    text = fileName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }

            if (uiState.isRunning && !uiState.isCancelling) {
                OutlinedButton(
                    onClick = viewModel::cancelMerge,
                    modifier = Modifier.padding(top = 32.dp),
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}
