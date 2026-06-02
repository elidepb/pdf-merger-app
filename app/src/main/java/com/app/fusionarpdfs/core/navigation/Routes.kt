package com.app.fusionarpdfs.core.navigation

private const val RESULT_ROUTE_PATTERN =
    "result?${NavArgs.HISTORY_ITEM_ID}={${NavArgs.HISTORY_ITEM_ID}}"

/**
 * Rutas centralizadas de navegación de la aplicación.
 */
sealed class Route(val path: String) {

    data object Home : Route("home")

    data object Reorder : Route("reorder")

    data object Preview : Route("preview")

    data object Progress : Route("progress")

    data object History : Route("history")

    data object Settings : Route("settings")

    data object PrivacyPolicy : Route("settings/privacy")

    data object OpenSourceLicenses : Route("settings/licenses")

    data object Result : Route(RESULT_ROUTE_PATTERN) {

        fun buildRoute(historyItemId: String? = null): String {
            return "result?${NavArgs.HISTORY_ITEM_ID}=${historyItemId.orEmpty()}"
        }
    }
}

internal val resultRoutePattern: String = RESULT_ROUTE_PATTERN
