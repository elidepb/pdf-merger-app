package com.app.fusionarpdfs.presentation.result

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.core.navigation.NavArgs
import com.app.fusionarpdfs.core.utils.toAccessErrorMessage
import com.app.fusionarpdfs.domain.repository.PdfFileRepository
import com.app.fusionarpdfs.domain.usecase.GetMergeResultUseCase
import com.app.fusionarpdfs.domain.usecase.OpenMergedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.ShareMergedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.StartNewMergeUseCase
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.ErrorDialogState
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
    private val pdfFileRepository: PdfFileRepository,
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

        viewModelScope.launch {
            openMergedPdfUseCase(Uri.parse(mergeResult.fileUri)).fold(
                onSuccess = {},
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            errorDialog = ErrorDialogState(
                                title = "No se pudo abrir",
                                message = error.toAccessErrorMessage(),
                            ),
                        )
                    }
                },
            )
        }
    }

    fun onSharePdf() {
        val mergeResult = _uiState.value.mergeResult ?: return

        viewModelScope.launch {
            shareMergedPdfUseCase(
                uri = Uri.parse(mergeResult.fileUri),
                fileName = mergeResult.fileName,
            ).fold(
                onSuccess = {},
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            errorDialog = ErrorDialogState(
                                title = "No se pudo compartir",
                                message = error.toAccessErrorMessage(),
                            ),
                        )
                    }
                },
            )
        }
    }

    fun onStartNewMerge(): ResultAction {
        startNewMergeUseCase()
        return ResultAction.NavigateToHome
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }

    fun onErrorDialogDismissed() {
        _uiState.update { it.copy(errorDialog = null) }
    }

    private fun loadResult() {
        viewModelScope.launch {
            val mergeResult = getMergeResultUseCase(resultId)
            if (mergeResult == null) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorDialog = ErrorDialogState(
                            title = "Resultado no disponible",
                            message = "No se encontró el PDF generado.",
                            confirmText = "Ir al inicio",
                            confirmAction = ErrorDialogAction.NavigateHome,
                        ),
                    )
                }
                return@launch
            }

            val isAccessible = pdfFileRepository.validatePdfAccessible(
                Uri.parse(mergeResult.fileUri),
            ).isSuccess

            _uiState.update {
                it.copy(
                    isLoading = false,
                    mergeResult = mergeResult,
                    isFileAccessible = isAccessible,
                    errorDialog = if (!isAccessible) {
                        ErrorDialogState(
                            title = "Archivo no disponible",
                            message = "El PDF ya no está accesible. Puede haber sido movido o eliminado.",
                            confirmText = "Entendido",
                        )
                    } else {
                        null
                    },
                )
            }
        }
    }
}
