package com.app.fusionarpdfs.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

private const val NAV_ANIMATION_DURATION_MS = 280

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(NAV_ANIMATION_DURATION_MS)) +
        slideInHorizontally(
            animationSpec = tween(NAV_ANIMATION_DURATION_MS),
            initialOffsetX = { fullWidth -> fullWidth / 4 },
        )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(NAV_ANIMATION_DURATION_MS)) +
        slideOutHorizontally(
            animationSpec = tween(NAV_ANIMATION_DURATION_MS),
            targetOffsetX = { fullWidth -> -fullWidth / 4 },
        )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(NAV_ANIMATION_DURATION_MS)) +
        slideInHorizontally(
            animationSpec = tween(NAV_ANIMATION_DURATION_MS),
            initialOffsetX = { fullWidth -> -fullWidth / 4 },
        )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.defaultPopExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(NAV_ANIMATION_DURATION_MS)) +
        slideOutHorizontally(
            animationSpec = tween(NAV_ANIMATION_DURATION_MS),
            targetOffsetX = { fullWidth -> fullWidth / 4 },
        )
}
