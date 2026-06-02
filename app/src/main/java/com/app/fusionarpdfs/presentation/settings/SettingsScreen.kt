package com.app.fusionarpdfs.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
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
import com.app.fusionarpdfs.presentation.settings.components.SettingsSectionHeader
import com.app.fusionarpdfs.presentation.settings.components.SettingsThemeSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPrivacyPolicy: () -> Unit,
    onNavigateToOpenSourceLicenses: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onUserMessageShown()
        }
    }

    if (uiState.showClearHistoryConfirmation) {
        AlertDialog(
            onDismissRequest = viewModel::onDismissClearHistoryConfirmation,
            title = { Text("Limpiar historial") },
            text = {
                Text(
                    "Se eliminarán todos los registros del historial. " +
                        "Esta acción no borra los archivos PDF guardados.",
                )
            },
            confirmButton = {
                TextButton(onClick = viewModel::onConfirmClearHistory) {
                    Text("Limpiar")
                }
            },
            dismissButton = {
                TextButton(onClick = viewModel::onDismissClearHistoryConfirmation) {
                    Text("Cancelar")
                }
            },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                SettingsSectionHeader(title = "Apariencia")
                SettingsThemeSelector(
                    selectedThemeMode = uiState.themeMode,
                    onThemeModeSelected = viewModel::onThemeModeSelected,
                )
            }

            item {
                SettingsSectionHeader(
                    title = "Preferencias",
                    modifier = Modifier.padding(top = 24.dp),
                )
                OutlinedTextField(
                    value = uiState.defaultPdfFileName,
                    onValueChange = viewModel::onDefaultPdfFileNameChange,
                    label = { Text("Nombre por defecto del PDF") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedButton(
                    onClick = viewModel::onDefaultPdfFileNameCommitted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    Text("Guardar nombre por defecto")
                }
                ListItem(
                    headlineContent = { Text("Confirmar antes de fusionar") },
                    supportingContent = {
                        Text("Muestra un diálogo de confirmación en la vista previa")
                    },
                    trailingContent = {
                        Switch(
                            checked = uiState.confirmBeforeMerge,
                            onCheckedChange = viewModel::onConfirmBeforeMergeChange,
                        )
                    },
                    modifier = Modifier.padding(top = 8.dp),
                )
            }

            item {
                SettingsSectionHeader(
                    title = "Datos",
                    modifier = Modifier.padding(top = 24.dp),
                )
                OutlinedButton(
                    onClick = viewModel::onShowClearHistoryConfirmation,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Limpiar historial")
                }
            }

            item {
                SettingsSectionHeader(
                    title = "Información",
                    modifier = Modifier.padding(top = 24.dp),
                )
                Column {
                    ListItem(
                        headlineContent = { Text("Política de privacidad") },
                        trailingContent = {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                            )
                        },
                        modifier = Modifier.clickable(onClick = onNavigateToPrivacyPolicy),
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("Licencias open source") },
                        trailingContent = {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                            )
                        },
                        modifier = Modifier.clickable(onClick = onNavigateToOpenSourceLicenses),
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("Versión") },
                        supportingContent = {
                            Text(
                                text = uiState.appVersion,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        },
                    )
                }
            }
        }
    }
}
