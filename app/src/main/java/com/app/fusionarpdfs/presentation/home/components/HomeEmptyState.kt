package com.app.fusionarpdfs.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.fusionarpdfs.core.accessibility.A11yLabels
import com.app.fusionarpdfs.core.theme.Spacing
import com.app.fusionarpdfs.presentation.common.components.AnimatedFadeIn
import com.app.fusionarpdfs.presentation.common.components.PdfFileIcon

@Composable
fun HomeEmptyState(
    onSelectPdfs: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedFadeIn(visible = true, modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(Spacing.xxl)
                .semantics { contentDescription = A11yLabels.EMPTY_HOME },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PdfFileIcon(
                modifier = Modifier.size(72.dp),
                decorative = false,
            )
            Spacer(modifier = Modifier.height(Spacing.xl))
            Text(
                text = "Sin PDFs seleccionados",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.semantics { heading() },
            )
            Spacer(modifier = Modifier.height(Spacing.sm))
            Text(
                text = "Elige uno o más archivos PDF para fusionarlos en un solo documento",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(Spacing.xxl))
            Button(
                onClick = onSelectPdfs,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ) {
                Icon(Icons.Default.UploadFile, contentDescription = null)
                Text(
                    text = "Seleccionar PDFs",
                    modifier = Modifier.padding(start = Spacing.sm),
                )
            }
        }
    }
}
