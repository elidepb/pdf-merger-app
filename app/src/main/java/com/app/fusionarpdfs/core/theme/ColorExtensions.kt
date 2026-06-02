package com.app.fusionarpdfs.core.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

fun Color.toCssHex(): String {
    val red = (red * 255f).toInt().coerceIn(0, 255)
    val green = (green * 255f).toInt().coerceIn(0, 255)
    val blue = (blue * 255f).toInt().coerceIn(0, 255)
    return String.format("#%02X%02X%02X", red, green, blue)
}

fun Color.isDarkColor(): Boolean = luminance() < 0.5f
