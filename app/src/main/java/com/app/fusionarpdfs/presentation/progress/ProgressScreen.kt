package com.app.fusionarpdfs.presentation.progress

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.core.accessibility.A11yLabels
import com.app.fusionarpdfs.core.theme.Spacing
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.components.AnimatedFadeIn
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
                .padding(Spacing.xl)
                .semantics { contentDescription = A11yLabels.MERGE_PROGRESS },
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
                modifier = Modifier.padding(top = Spacing.xl),
            )

            if (uiState.totalSteps > 0) {
                LinearProgressIndicator(
                    progress = { uiState.progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.xl),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                Text(
                    text = "${uiState.currentStep} de ${uiState.totalSteps} documentos",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = Spacing.sm),
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
                    modifier = Modifier.padding(top = Spacing.sm),
                )
            }

            AnimatedFadeIn(visible = uiState.isRunning && !uiState.isCancelling) {
                OutlinedButton(
                    onClick = viewModel::cancelMerge,
                    modifier = Modifier.padding(top = Spacing.xxl),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(A11yLabels.CANCEL_MERGE)
                }
            }
        }
    }
}
