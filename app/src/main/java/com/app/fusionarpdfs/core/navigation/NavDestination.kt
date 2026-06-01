package com.app.fusionarpdfs.core.navigation

sealed interface NavDestination {

    data object Home : NavDestination

    data object Reorder : NavDestination

    data object Preview : NavDestination

    data object Progress : NavDestination

    data class Result(
        val historyItemId: String? = null,
    ) : NavDestination

    data object History : NavDestination

    data object Settings : NavDestination
}
