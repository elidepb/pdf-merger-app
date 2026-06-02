package com.app.fusionarpdfs.presentation.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.core.utils.dialogTitle
import com.app.fusionarpdfs.core.utils.isRetryable
import com.app.fusionarpdfs.core.utils.toUserMessage
import com.app.fusionarpdfs.domain.model.MergeError
import com.app.fusionarpdfs.domain.model.MergeException
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import com.app.fusionarpdfs.domain.usecase.ExecuteMergeFromSessionUseCase
import com.app.fusionarpdfs.domain.usecase.SaveHistoryUseCase
import com.app.fusionarpdfs.presentation.common.ErrorDialogAction
import com.app.fusionarpdfs.presentation.common.ErrorDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val mergeSessionRepository: MergeSessionRepository,
    private val executeMergeFromSessionUseCase: ExecuteMergeFromSessionUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()

    private var mergeJob: Job? = null

    init {
        startMerge()
    }

    fun startMerge() {
        if (mergeJob?.isActive == true) return

        mergeSessionRepository.setLastMergeResult(null)

        mergeJob = viewModelScope.launch {
            _uiState.update {
                ProgressUiState(
                    isRunning = true,
                    statusMessage = "Preparando fusión…",
                )
            }

            val pdfs = mergeSessionRepository.selectedPdfs.value

            try {
                val result = executeMergeFromSessionUseCase { currentIndex, total ->
                    val displayIndex = currentIndex.coerceAtMost(total)
                    val fileName = pdfs.getOrNull(currentIndex)?.name
                    val fraction = if (total <= 0) {
                        0f
                    } else {
                        displayIndex.toFloat() / total.toFloat()
                    }

                    _uiState.update { state ->
                        state.copy(
                            currentStep = displayIndex,
                            totalSteps = total,
                            currentFileName = fileName,
                            progressFraction = fraction.coerceIn(0f, 1f),
                            statusMessage = when {
                                currentIndex >= total -> "Finalizando…"
                                total == 0 -> "Procesando…"
                                else -> "Procesando ${currentIndex + 1} de $total"
                            },
                        )
                    }
                }

                result.fold(
                    onSuccess = { mergeResult ->
                        saveHistoryUseCase(mergeResult)
                        mergeSessionRepository.setLastMergeResult(mergeResult)
                        _uiState.update {
                            it.copy(
                                isRunning = false,
                                isCancelling = false,
                                progressFraction = 1f,
                                statusMessage = "Fusión completada",
                                completedResultId = mergeResult.id,
                            )
                        }
                    },
                    onFailure = { throwable ->
                        handleFailure(throwable)
                    },
                )
            } catch (cancellation: CancellationException) {
                handleFailure(cancellation)
            }
        }
    }

    fun cancelMerge() {
        if (!_uiState.value.isRunning || _uiState.value.isCancelling) return

        _uiState.update {
            it.copy(
                isCancelling = true,
                statusMessage = "Cancelando…",
            )
        }
        mergeJob?.cancel()
    }

    fun onErrorDialogDismissed() {
        _uiState.update { it.copy(errorDialog = null) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(completedResultId = null) }
    }

    private fun handleFailure(throwable: Throwable) {
        val mergeError = when (throwable) {
            is CancellationException -> MergeError.CANCELLED
            is MergeException -> throwable.error
            else -> MergeError.UNKNOWN
        }

        val retryable = mergeError.isRetryable()

        _uiState.update {
            it.copy(
                isRunning = false,
                isCancelling = false,
                errorDialog = ErrorDialogState(
                    title = mergeError.dialogTitle(),
                    message = mergeError.toUserMessage(),
                    confirmText = if (retryable) "Reintentar" else "Volver",
                    dismissText = if (retryable) "Volver" else null,
                    confirmAction = if (retryable) ErrorDialogAction.Retry else ErrorDialogAction.NavigateBack,
                    dismissAction = if (retryable) ErrorDialogAction.NavigateBack else null,
                ),
            )
        }
    }
}
