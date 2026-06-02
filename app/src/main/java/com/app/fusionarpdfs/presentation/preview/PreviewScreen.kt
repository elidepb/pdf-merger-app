package com.app.fusionarpdfs.presentation.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.CallMerge
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.core.utils.formatFileSize
import com.app.fusionarpdfs.presentation.common.components.AppFeedbackHandler
import com.app.fusionarpdfs.presentation.pdf.rememberPdfSaveLocationPicker
import com.app.fusionarpdfs.presentation.preview.components.PreviewPdfSummaryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToProgress: () -> Unit,
    viewModel: PreviewViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    val saveLocationPicker = rememberPdfSaveLocationPicker { uri ->
        viewModel.onOutputLocationSelected(uri)
    }

    LaunchedEffect(uiState.pdfs.isEmpty()) {
        if (uiState.pdfs.isEmpty()) {
            onNavigateBack()
        }
    }

    AppFeedbackHandler(
        snackbarHostState = snackbarHostState,
        snackbarMessage = null,
        onSnackbarShown = {},
        errorDialog = uiState.errorDialog,
        onErrorDialogDismiss = viewModel::onErrorDialogDismissed,
        onErrorDialogAction = { _ -> },
    )

    if (uiState.showMergeConfirmation) {
        AlertDialog(
            onDismissRequest = viewModel::onDismissMergeConfirmation,
            title = { Text("Confirmar fusión") },
            text = {
                Text(
                    "Se fusionarán ${uiState.pdfs.size} archivos en \"${uiState.outputFileName}\". " +
                        "¿Deseas continuar?",
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        when (val action = viewModel.onConfirmMerge()) {
                            PreviewMergeAction.NavigateToProgress -> onNavigateToProgress()
                            PreviewMergeAction.None -> Unit
                            is PreviewMergeAction.ShowMessage -> Unit
                        }
                    },
                ) {
                    Text("Fusionar")
                }
            },
            dismissButton = {
                TextButton(onClick = viewModel::onDismissMergeConfirmation) {
                    Text("Cancelar")
                }
            },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vista previa") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (uiState.pdfs.isNotEmpty()) {
                Button(
                    onClick = {
                        when (val action = viewModel.onMergeClick()) {
                            PreviewMergeAction.NavigateToProgress -> onNavigateToProgress()
                            PreviewMergeAction.None -> Unit
                            is PreviewMergeAction.ShowMessage -> Unit
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Icon(Icons.AutoMirrored.Filled.CallMerge, contentDescription = null)
                    Text(
                        text = "Fusionar",
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                Text(
                    text = "Resumen",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "${uiState.pdfs.size} archivos · ${formatFileSize(uiState.totalSizeBytes)} total",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                )
            }

            itemsIndexed(
                items = uiState.pdfs,
                key = { _, pdf -> pdf.id },
            ) { index, pdf ->
                PreviewPdfSummaryItem(
                    pdf = pdf,
                    position = index + 1,
                )
                if (index < uiState.pdfs.lastIndex) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                }
            }

            item {
                Text(
                    text = "Configuración de salida",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
                )

                OutlinedTextField(
                    value = uiState.outputFileName,
                    onValueChange = viewModel::onFileNameChange,
                    label = { Text("Nombre del archivo") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )

                OutlinedButton(
                    onClick = {
                        saveLocationPicker.openPicker(viewModel.onSelectLocationClick())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                ) {
                    Icon(Icons.Default.FolderOpen, contentDescription = null)
                    Text(
                        text = if (uiState.hasOutputLocation) {
                            uiState.outputLocationLabel ?: "Ubicación seleccionada"
                        } else {
                            "Seleccionar ubicación"
                        },
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }

                if (uiState.hasOutputLocation) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                        ),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Listo para guardar",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Text(
                                text = uiState.outputFileName,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
