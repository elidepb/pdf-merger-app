package com.app.fusionarpdfs.presentation.reorder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.presentation.reorder.components.ReorderPdfListItem
import kotlinx.coroutines.launch
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReorderScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPreview: () -> Unit,
    viewModel: ReorderViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val hapticFeedback = LocalHapticFeedback.current
    val lazyListState = rememberLazyListState()

    val reorderableLazyListState = rememberReorderableLazyListState(lazyListState) { from, to ->
        viewModel.onMove(from.index, to.index)
        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
    }

    LaunchedEffect(uiState.pdfs.isEmpty()) {
        if (uiState.pdfs.isEmpty()) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reordenar PDFs") },
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Arrastra para cambiar el orden de fusión",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    Button(
                        onClick = {
                            when (val action = viewModel.onPreviewClick()) {
                                ReorderPreviewAction.NavigateToPreview -> onNavigateToPreview()
                                is ReorderPreviewAction.ShowMessage -> {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(action.message)
                                    }
                                }
                            }
                        },
                        enabled = uiState.canContinue,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Vista previa")
                    }
                }
            }
        },
    ) { innerPadding ->
        if (uiState.pdfs.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                state = lazyListState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                itemsIndexed(
                    items = uiState.pdfs,
                    key = { _, pdf -> pdf.id },
                ) { index, pdf ->
                    ReorderableItem(
                        state = reorderableLazyListState,
                        key = pdf.id,
                    ) { isDragging ->
                        ReorderPdfListItem(
                            pdf = pdf,
                            position = index + 1,
                            isDragging = isDragging,
                            onRemove = { viewModel.onRemovePdf(pdf.id) },
                            dragHandleModifier = Modifier.draggableHandle(),
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "No hay PDFs para reordenar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
