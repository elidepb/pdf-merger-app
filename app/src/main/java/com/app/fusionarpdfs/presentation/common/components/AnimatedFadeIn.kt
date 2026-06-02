package com.app.fusionarpdfs.presentation.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimatedFadeIn(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn(animationSpec = tween(350)) +
            slideInVertically(
                animationSpec = tween(350),
                initialOffsetY = { offset -> offset / 8 },
            ),
        exit = fadeOut(animationSpec = tween(200)),
    ) {
        content()
    }
}
