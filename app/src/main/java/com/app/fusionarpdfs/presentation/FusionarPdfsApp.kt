package com.app.fusionarpdfs.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.fusionarpdfs.core.navigation.FusionarPdfsNavGraph
import com.app.fusionarpdfs.core.theme.FusionarPdfsTheme
import com.app.fusionarpdfs.domain.model.AppThemeMode

@Composable
fun FusionarPdfsApp(
    viewModel: AppViewModel = hiltViewModel(),
) {
    val userPreferences by viewModel.userPreferences.collectAsStateWithLifecycle()
    val darkTheme = when (userPreferences.themeMode) {
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    FusionarPdfsTheme(darkTheme = darkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            FusionarPdfsNavGraph()
        }
    }
}
