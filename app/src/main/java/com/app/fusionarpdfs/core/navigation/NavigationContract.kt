package com.app.fusionarpdfs.core.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions

/**
 * Contrato de navegación tipada para el flujo de la aplicación.
 */
fun NavHostController.navigateTo(destination: NavDestination) {
    when (destination) {
        NavDestination.Home -> navigateToHome()

        NavDestination.Reorder -> navigate(Route.Reorder.path)

        NavDestination.Preview -> navigate(Route.Preview.path)

        NavDestination.Progress -> navigate(Route.Progress.path)

        is NavDestination.Result -> navigate(Route.Result.buildRoute(destination.historyItemId))

        NavDestination.History -> navigate(Route.History.path)

        NavDestination.Settings -> navigate(Route.Settings.path)
    }
}

fun NavHostController.navigateToHome(clearBackStack: Boolean = false) {
    if (clearBackStack) {
        navigate(
            route = Route.Home.path,
            navOptions = navOptions {
                popUpTo(graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            },
        )
    } else {
        navigate(Route.Home.path)
    }
}

fun NavHostController.navigateToReorder() {
    navigate(Route.Reorder.path)
}

fun NavHostController.navigateToPreview() {
    navigate(Route.Preview.path)
}

fun NavHostController.navigateToProgress() {
    navigate(Route.Progress.path)
}

fun NavHostController.navigateToResult(historyItemId: String? = null) {
    navigate(
        route = Route.Result.buildRoute(historyItemId),
        navOptions = navOptions {
            popUpTo(Route.Home.path) { inclusive = false }
        },
    )
}

fun NavHostController.navigateToHistory() {
    navigate(Route.History.path)
}

fun NavHostController.navigateToSettings() {
    navigate(Route.Settings.path)
}

fun NavHostController.navigateBack() {
    popBackStack()
}
