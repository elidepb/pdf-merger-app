package com.app.fusionarpdfs.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = SurfaceLight,
    primaryContainer = PrimaryBlue.copy(alpha = 0.12f),
    onPrimaryContainer = PrimaryBlueDark,
    secondary = SecondaryGreen,
    onSecondary = SurfaceLight,
    secondaryContainer = SecondaryGreen.copy(alpha = 0.12f),
    onSecondaryContainer = SecondaryGreen,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = SurfaceLight,
    onSurface = TextPrimary,
    surfaceVariant = BackgroundLight,
    onSurfaceVariant = TextSecondary,
    error = ErrorRed,
    onError = SurfaceLight,
    outline = BorderLight,
    outlineVariant = BorderLight.copy(alpha = 0.6f),
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlueLight,
    onPrimary = TextPrimary,
    primaryContainer = PrimaryBlueDark,
    onPrimaryContainer = TextPrimaryDark,
    secondary = SecondaryGreen,
    onSecondary = TextPrimary,
    secondaryContainer = SecondaryGreen.copy(alpha = 0.18f),
    onSecondaryContainer = SecondaryGreen,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceDarkElevated,
    onSurfaceVariant = TextSecondaryDark,
    error = ErrorRed,
    onError = TextPrimaryDark,
    outline = BorderDark,
    outlineVariant = BorderDark.copy(alpha = 0.6f),
)

@Composable
fun FusionarPdfsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FusionarPdfsTypography,
        shapes = FusionarPdfsShapes,
        content = content,
    )
}
