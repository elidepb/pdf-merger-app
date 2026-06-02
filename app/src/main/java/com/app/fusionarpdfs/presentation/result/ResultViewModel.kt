package com.app.fusionarpdfs.presentation.result

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.core.navigation.NavArgs
import com.app.fusionarpdfs.domain.usecase.GetMergeResultUseCase
import com.app.fusionarpdfs.domain.usecase.OpenMergedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.ShareMergedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.StartNewMergeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMergeResultUseCase: GetMergeResultUseCase,
    private val openMergedPdfUseCase: OpenMergedPdfUseCase,
    private val shareMergedPdfUseCase: ShareMergedPdfUseCase,
    private val startNewMergeUseCase: StartNewMergeUseCase,
) : ViewModel() {

    private val resultId = savedStateHandle.get<String>(NavArgs.HISTORY_ITEM_ID)
        ?.takeIf { it.isNotBlank() }

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    init {
        loadResult()
    }

    fun onOpenPdf() {
        val mergeResult = _uiState.value.mergeResult ?: return

        openMergedPdfUseCase(Uri.parse(mergeResult.fileUri)).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update {
                    it.copy(userMessage = "No hay una aplicación disponible para abrir PDFs")
                }
            },
        )
    }

    fun onSharePdf() {
        val mergeResult = _uiState.value.mergeResult ?: return

        shareMergedPdfUseCase(
            uri = Uri.parse(mergeResult.fileUri),
            fileName = mergeResult.fileName,
        ).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update {
                    it.copy(userMessage = "No hay una aplicación disponible para compartir")
                }
            },
        )
    }

    fun onStartNewMerge(): ResultAction {
        startNewMergeUseCase()
        return ResultAction.NavigateToHome
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }

    fun onErrorShown() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    private fun loadResult() {
        viewModelScope.launch {
            val mergeResult = getMergeResultUseCase(resultId)
            _uiState.update {
                if (mergeResult == null) {
                    it.copy(
                        isLoading = false,
                        errorMessage = "No se encontró el PDF generado",
                    )
                } else {
                    it.copy(
                        isLoading = false,
                        mergeResult = mergeResult,
                    )
                }
            }
        }
    }
}
