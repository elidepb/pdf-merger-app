package com.app.fusionarpdfs.presentation.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.domain.model.AppThemeMode
import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository
import com.app.fusionarpdfs.domain.usecase.ClearHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val clearHistoryUseCase: ClearHistoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(
            appVersion = context.packageManager
                .getPackageInfo(context.packageName, 0)
                .versionName
                .orEmpty(),
        ),
    )
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesRepository.preferencesFlow.collect { preferences ->
                _uiState.update {
                    it.copy(
                        themeMode = preferences.themeMode,
                        defaultPdfFileName = preferences.defaultPdfFileName,
                        confirmBeforeMerge = preferences.confirmBeforeMerge,
                    )
                }
            }
        }
    }

    fun onThemeModeSelected(themeMode: AppThemeMode) {
        viewModelScope.launch {
            userPreferencesRepository.setThemeMode(themeMode)
        }
    }

    fun onDefaultPdfFileNameChange(fileName: String) {
        _uiState.update { it.copy(defaultPdfFileName = fileName) }
    }

    fun onDefaultPdfFileNameCommitted() {
        viewModelScope.launch {
            userPreferencesRepository.setDefaultPdfFileName(_uiState.value.defaultPdfFileName)
        }
    }

    fun onConfirmBeforeMergeChange(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setConfirmBeforeMerge(enabled)
        }
    }

    fun onShowClearHistoryConfirmation() {
        _uiState.update { it.copy(showClearHistoryConfirmation = true) }
    }

    fun onDismissClearHistoryConfirmation() {
        _uiState.update { it.copy(showClearHistoryConfirmation = false) }
    }

    fun onConfirmClearHistory() {
        viewModelScope.launch {
            _uiState.update { it.copy(showClearHistoryConfirmation = false) }
            clearHistoryUseCase().fold(
                onSuccess = {
                    _uiState.update { it.copy(userMessage = "Historial limpiado") }
                },
                onFailure = {
                    _uiState.update { it.copy(userMessage = "No se pudo limpiar el historial") }
                },
            )
        }
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }
}
