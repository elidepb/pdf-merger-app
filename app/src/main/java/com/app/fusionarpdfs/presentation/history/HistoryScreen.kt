package com.app.fusionarpdfs.presentation.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.app.fusionarpdfs.core.accessibility.A11yLabels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.presentation.common.components.AppFeedbackHandler
import com.app.fusionarpdfs.presentation.history.components.HistoryEmptyState
import com.app.fusionarpdfs.presentation.history.components.HistoryListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onNavigateBack: () -> Unit,
    onNavigateToResult: (historyItemId: String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    AppFeedbackHandler(
        snackbarHostState = snackbarHostState,
        snackbarMessage = uiState.userMessage,
        onSnackbarShown = viewModel::onUserMessageShown,
        errorDialog = uiState.errorDialog,
        onErrorDialogDismiss = viewModel::onErrorDialogDismissed,
        onErrorDialogAction = { _ -> },
    )

    if (uiState.showClearConfirmation) {
        AlertDialog(
            onDismissRequest = viewModel::onDismissClearConfirmation,
            title = { Text("Limpiar historial") },
            text = { Text("Se eliminarán todos los registros del historial. Esta acción no borra los archivos PDF guardados.") },
            confirmButton = {
                TextButton(onClick = viewModel::onConfirmClearHistory) {
                    Text("Limpiar")
                }
            },
            dismissButton = {
                TextButton(onClick = viewModel::onDismissClearConfirmation) {
                    Text("Cancelar")
                }
            },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = A11yLabels.BACK)
                    }
                },
                actions = {
                    if (!uiState.isEmpty) {
                        IconButton(onClick = viewModel::onShowClearConfirmation) {
                            Icon(Icons.Default.DeleteSweep, contentDescription = "Limpiar historial")
                        }
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        if (uiState.isEmpty) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                HistoryEmptyState()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(
                    items = uiState.items,
                    key = { it.id },
                ) { item ->
                    HistoryListItem(
                        item = item,
                        onItemClick = { onNavigateToResult(item.id) },
                        onOpen = { viewModel.onOpenPdf(item) },
                        onShare = { viewModel.onSharePdf(item) },
                        onDelete = { viewModel.onDeleteItem(item.id) },
                        modifier = Modifier
                            .animateItem()
                            .padding(bottom = 12.dp),
                    )
                }
            }
        }
    }
}
