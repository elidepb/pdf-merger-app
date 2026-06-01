package com.app.fusionarpdfs.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.presentation.home.components.HomeEmptyState
import com.app.fusionarpdfs.presentation.home.components.HomePdfListItem
import com.app.fusionarpdfs.presentation.pdf.rememberPdfDocumentPicker
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToReorder: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val pdfPicker = rememberPdfDocumentPicker { uris ->
        viewModel.onPdfsSelected(uris)
    }

    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onUserMessageShown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fusionar PDFs") },
                actions = {
                    if (!uiState.isEmpty) {
                        IconButton(onClick = viewModel::onClearSelection) {
                            Icon(Icons.Default.DeleteSweep, contentDescription = "Limpiar selección")
                        }
                    }
                    IconButton(onClick = onNavigateToHistory) {
                        Icon(Icons.Default.History, contentDescription = "Historial")
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            if (!uiState.isEmpty) {
                FloatingActionButton(onClick = pdfPicker.openPicker) {
                    Icon(Icons.Default.UploadFile, contentDescription = "Agregar PDFs")
                }
            }
        },
        bottomBar = {
            if (!uiState.isEmpty) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text(
                        text = uiState.pdfCountLabel,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    Button(
                        onClick = {
                            when (val action = viewModel.onContinueClick()) {
                                HomeContinueAction.NavigateToReorder -> onNavigateToReorder()
                                is HomeContinueAction.ShowMessage -> {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(action.message)
                                    }
                                }
                            }
                        },
                        enabled = uiState.canContinue,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Continuar")
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (uiState.isEmpty) {
                HomeEmptyState(
                    onSelectPdfs = pdfPicker.openPicker,
                    modifier = Modifier.align(Alignment.Center),
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(
                        items = uiState.pdfs,
                        key = { it.id },
                    ) { pdf ->
                        HomePdfListItem(
                            pdf = pdf,
                            onRemove = { viewModel.onRemovePdf(pdf.id) },
                            modifier = Modifier.padding(bottom = 12.dp),
                        )
                    }
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}
