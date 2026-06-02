package com.app.fusionarpdfs.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.fusionarpdfs.presentation.history.HistoryScreen
import com.app.fusionarpdfs.presentation.home.HomeScreen
import com.app.fusionarpdfs.presentation.preview.PreviewScreen
import com.app.fusionarpdfs.presentation.progress.ProgressScreen
import com.app.fusionarpdfs.presentation.reorder.ReorderScreen
import com.app.fusionarpdfs.presentation.result.ResultScreen
import com.app.fusionarpdfs.presentation.settings.SettingsScreen

@Composable
fun FusionarPdfsNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.path,
    ) {
        composable(Route.Home.path) {
            HomeScreen(
                onNavigateToReorder = { navController.navigateToReorder() },
                onNavigateToHistory = { navController.navigateToHistory() },
                onNavigateToSettings = { navController.navigateToSettings() },
            )
        }

        composable(Route.Reorder.path) {
            ReorderScreen(
                onNavigateBack = { navController.navigateBack() },
                onNavigateToPreview = { navController.navigateToPreview() },
            )
        }

        composable(Route.Preview.path) {
            PreviewScreen(
                onNavigateBack = { navController.navigateBack() },
                onNavigateToProgress = { navController.navigateToProgress() },
            )
        }

        composable(Route.Progress.path) {
            ProgressScreen(
                onNavigateToResult = { resultId ->
                    navController.navigateToResult(historyItemId = resultId)
                },
                onNavigateBack = { navController.navigateBack() },
            )
        }

        composable(
            route = resultRoutePattern,
            arguments = listOf(
                navArgument(NavArgs.HISTORY_ITEM_ID) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
            ),
        ) { backStackEntry ->
            ResultScreen(
                onNavigateToHome = { navController.navigateToHome(clearBackStack = true) },
                onNavigateToHistory = { navController.navigateToHistory() },
            )
        }

        composable(Route.History.path) {
            HistoryScreen(
                onNavigateBack = { navController.navigateBack() },
                onNavigateToResult = { historyItemId ->
                    navController.navigateToResult(historyItemId = historyItemId)
                },
            )
        }

        composable(Route.Settings.path) {
            SettingsScreen(
                onNavigateBack = { navController.navigateBack() },
            )
        }
    }
}
