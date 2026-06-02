package com.app.fusionarpdfs.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.core.utils.formatFileSize
import com.app.fusionarpdfs.core.utils.formatMergeDate
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.components.AppFeedbackHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToHistory: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    AppFeedbackHandler(
        snackbarHostState = snackbarHostState,
        snackbarMessage = uiState.userMessage,
        onSnackbarShown = viewModel::onUserMessageShown,
        errorDialog = uiState.errorDialog,
        onErrorDialogDismiss = viewModel::onErrorDialogDismissed,
        onErrorDialogAction = { action ->
            if (action == ErrorDialogAction.NavigateHome) {
                onNavigateToHome()
            }
        },
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resultado") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.mergeResult != null -> {
                val mergeResult = uiState.mergeResult!!
                val actionsEnabled = uiState.isFileAccessible

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                    Text(
                        text = "PDF fusionado correctamente",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp),
                    )

                    if (!actionsEnabled) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f),
                            ),
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                )
                                Text(
                                    text = "El archivo ya no está accesible en su ubicación original.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(top = 8.dp),
                                )
                            }
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = mergeResult.fileName,
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = formatFileSize(mergeResult.fileSizeBytes),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                            Text(
                                text = formatMergeDate(mergeResult.createdAt),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }

                    Button(
                        onClick = viewModel::onOpenPdf,
                        enabled = actionsEnabled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                    ) {
                        Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null)
                        Text(
                            text = "Abrir PDF",
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }

                    OutlinedButton(
                        onClick = viewModel::onSharePdf,
                        enabled = actionsEnabled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null)
                        Text(
                            text = "Compartir",
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }

                    Button(
                        onClick = {
                            when (viewModel.onStartNewMerge()) {
                                ResultAction.NavigateToHome -> onNavigateToHome()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                    ) {
                        Text("Nueva fusión")
                    }

                    OutlinedButton(
                        onClick = onNavigateToHistory,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                    ) {
                        Text("Ver historial")
                    }
                }
            }
        }
    }
}
