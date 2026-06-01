package com.app.fusionarpdfs.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                onNavigateToReorder = { navController.navigate(Route.Reorder.path) },
                onNavigateToHistory = { navController.navigate(Route.History.path) },
                onNavigateToSettings = { navController.navigate(Route.Settings.path) },
            )
        }

        composable(Route.Reorder.path) {
            ReorderScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPreview = { navController.navigate(Route.Preview.path) },
            )
        }

        composable(Route.Preview.path) {
            PreviewScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToProgress = { navController.navigate(Route.Progress.path) },
            )
        }

        composable(Route.Progress.path) {
            ProgressScreen(
                onNavigateToResult = {
                    navController.navigate(Route.Result.path) {
                        popUpTo(Route.Home.path) { inclusive = false }
                    }
                },
            )
        }

        composable(Route.Result.path) {
            ResultScreen(
                onNavigateToHome = {
                    navController.navigate(Route.Home.path) {
                        popUpTo(Route.Home.path) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavigateToHistory = { navController.navigate(Route.History.path) },
            )
        }

        composable(Route.History.path) {
            HistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToResult = { navController.navigate(Route.Result.path) },
            )
        }

        composable(Route.Settings.path) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}
