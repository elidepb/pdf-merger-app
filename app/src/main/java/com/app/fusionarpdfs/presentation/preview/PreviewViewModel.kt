package com.app.fusionarpdfs.presentation.preview

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.core.constants.AppConstants
import com.app.fusionarpdfs.core.utils.normalizePdfFileName
import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.MergeConfigurationError
import com.app.fusionarpdfs.domain.model.MergeConfigurationValidation
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.repository.UserPreferencesRepository
import com.app.fusionarpdfs.domain.usecase.PersistOutputUriPermissionUseCase
import com.app.fusionarpdfs.domain.usecase.SaveMergeConfigurationUseCase
import com.app.fusionarpdfs.domain.usecase.ValidateMergeConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PreviewViewModel @Inject constructor(
    mergeSessionRepository: MergeSessionRepository,
    userPreferencesRepository: UserPreferencesRepository,
    private val validateMergeConfigurationUseCase: ValidateMergeConfigurationUseCase,
    private val saveMergeConfigurationUseCase: SaveMergeConfigurationUseCase,
    private val persistOutputUriPermissionUseCase: PersistOutputUriPermissionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PreviewUiState())
    val uiState: StateFlow<PreviewUiState> = _uiState.asStateFlow()

    private var hasInitializedDefaultFileName = false

    init {
        viewModelScope.launch {
            mergeSessionRepository.selectedPdfs.collect { pdfs ->
                _uiState.update { state ->
                    state.copy(
                        pdfs = pdfs,
                        totalSizeBytes = pdfs.sumOf { it.sizeBytes },
                    )
                }
            }
        }

        viewModelScope.launch {
            mergeSessionRepository.mergeConfiguration.collect { configuration ->
                if (configuration == null) return@collect
                _uiState.update { state ->
                    state.copy(
                        outputFileName = configuration.outputFileName,
                        outputUri = configuration.outputUri,
                        outputLocationLabel = configuration.outputUri?.toOutputLocationLabel(),
                    )
                }
            }
        }

        viewModelScope.launch {
            userPreferencesRepository.preferencesFlow.collect { preferences ->
                if (!hasInitializedDefaultFileName && _uiState.value.outputUri.isNullOrBlank()) {
                    hasInitializedDefaultFileName = true
                    _uiState.update {
                        it.copy(
                            confirmBeforeMerge = preferences.confirmBeforeMerge,
                            outputFileName = preferences.defaultPdfFileName,
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(confirmBeforeMerge = preferences.confirmBeforeMerge)
                    }
                }
            }
        }
    }

    fun onFileNameChange(fileName: String) {
        _uiState.update { it.copy(outputFileName = fileName) }
    }

    fun onOutputLocationSelected(uri: Uri) {
        persistOutputUriPermissionUseCase(uri)
        _uiState.update {
            it.copy(
                outputUri = uri.toString(),
                outputLocationLabel = uri.toString().toOutputLocationLabel(),
            )
        }
    }

    fun onSelectLocationClick(): String {
        return normalizePdfFileName(_uiState.value.outputFileName)
            .ifBlank { AppConstants.DEFAULT_MERGED_FILE_NAME }
    }

    fun onMergeClick(): PreviewMergeAction {
        val configuration = validateMergeConfigurationUseCase.normalizedConfiguration(
            MergeConfiguration(
                outputFileName = _uiState.value.outputFileName,
                outputUri = _uiState.value.outputUri,
            ),
        )

        return when (
            val validation = validateMergeConfigurationUseCase(
                pdfs = _uiState.value.pdfs,
                configuration = configuration,
            )
        ) {
            MergeConfigurationValidation.Valid -> {
                if (_uiState.value.confirmBeforeMerge) {
                    _uiState.update {
                        it.copy(
                            showMergeConfirmation = true,
                            pendingConfiguration = configuration,
                        )
                    }
                    PreviewMergeAction.None
                } else {
                    proceedWithMerge(configuration)
                }
            }

            is MergeConfigurationValidation.Invalid -> {
                PreviewMergeAction.ShowMessage(validation.error.toUserMessage())
            }
        }
    }

    fun onConfirmMerge(): PreviewMergeAction {
        val configuration = _uiState.value.pendingConfiguration ?: return PreviewMergeAction.None
        _uiState.update { it.copy(showMergeConfirmation = false, pendingConfiguration = null) }
        return proceedWithMerge(configuration)
    }

    fun onDismissMergeConfirmation() {
        _uiState.update { it.copy(showMergeConfirmation = false, pendingConfiguration = null) }
    }

    private fun proceedWithMerge(configuration: MergeConfiguration): PreviewMergeAction {
        saveMergeConfigurationUseCase(configuration)
        _uiState.update {
            it.copy(
                outputFileName = configuration.outputFileName,
                outputUri = configuration.outputUri,
                outputLocationLabel = configuration.outputUri?.toOutputLocationLabel(),
            )
        }
        return PreviewMergeAction.NavigateToProgress
    }
}

private fun String.toOutputLocationLabel(): String {
    return Uri.parse(this).lastPathSegment ?: "Ubicación seleccionada"
}

private fun MergeConfigurationError.toUserMessage(): String {
    return when (this) {
        MergeConfigurationError.TOO_FEW_FILES -> "Selecciona al menos 2 PDFs para continuar"
        MergeConfigurationError.DUPLICATE_FILES -> "Hay archivos duplicados en la selección"
        MergeConfigurationError.EMPTY_FILE_NAME -> "Ingresa un nombre para el archivo"
        MergeConfigurationError.INVALID_FILE_NAME -> "El nombre del archivo no es válido"
        MergeConfigurationError.MISSING_OUTPUT_LOCATION -> "Selecciona dónde guardar el PDF"
    }
}
