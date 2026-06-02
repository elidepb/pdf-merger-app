package com.app.fusionarpdfs.presentation.history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
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

@Composable
fun HistoryEmptyState(
    modifier: Modifier = Modifier,
) {
    AnimatedFadeIn(visible = true, modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(Spacing.xxl)
                .semantics { contentDescription = A11yLabels.EMPTY_HISTORY },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = null,
                modifier = Modifier.padding(bottom = Spacing.lg),
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Sin fusiones registradas",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.semantics { heading() },
            )
            Text(
                text = "Los PDFs que fusiones aparecerán aquí",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = Spacing.sm),
            )
        }
    }
}
