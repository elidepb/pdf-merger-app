package com.app.fusionarpdfs.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    historyItemId: String? = null,
    onNavigateToHome: () -> Unit,
    onNavigateToHistory: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resultado") })
        },
    ) { innerPadding ->
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
                text = if (historyItemId != null) {
                    "PDF del historial ($historyItemId)"
                } else {
                    "PDF fusionado correctamente"
                },
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp),
            )
            Button(
                onClick = onNavigateToHome,
                modifier = Modifier.padding(top = 32.dp),
            ) {
                Text("Nueva fusión")
            }
            OutlinedButton(
                onClick = onNavigateToHistory,
                modifier = Modifier.padding(top = 8.dp),
            ) {
                Text("Ver historial")
            }
        }
    }
}
