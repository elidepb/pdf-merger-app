package com.app.fusionarpdfs.core.navigation

/**
 * Rutas centralizadas de navegación de la aplicación.
 */
sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Reorder : Route("reorder")
    data object Preview : Route("preview")
    data object Progress : Route("progress")
    data object Result : Route("result")
    data object History : Route("history")
    data object Settings : Route("settings")
}
