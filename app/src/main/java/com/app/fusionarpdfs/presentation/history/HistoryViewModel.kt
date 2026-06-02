package com.app.fusionarpdfs.presentation.history

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import com.app.fusionarpdfs.domain.usecase.ClearHistoryUseCase
import com.app.fusionarpdfs.domain.usecase.DeleteHistoryItemUseCase
import com.app.fusionarpdfs.domain.usecase.OpenMergedPdfUseCase
import com.app.fusionarpdfs.domain.usecase.ShareMergedPdfUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    mergeHistoryRepository: MergeHistoryRepository,
    private val deleteHistoryItemUseCase: DeleteHistoryItemUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase,
    private val openMergedPdfUseCase: OpenMergedPdfUseCase,
    private val shareMergedPdfUseCase: ShareMergedPdfUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            mergeHistoryRepository.observeHistory().collect { items ->
                _uiState.update {
                    it.copy(
                        items = items.sortedByDescending { item -> item.createdAt },
                    )
                }
            }
        }
    }

    fun onOpenPdf(item: MergeHistoryItem) {
        openMergedPdfUseCase(Uri.parse(item.fileUri)).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update {
                    it.copy(userMessage = "No hay una aplicación disponible para abrir PDFs")
                }
            },
        )
    }

    fun onSharePdf(item: MergeHistoryItem) {
        shareMergedPdfUseCase(
            uri = Uri.parse(item.fileUri),
            fileName = item.fileName,
        ).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update {
                    it.copy(userMessage = "No hay una aplicación disponible para compartir")
                }
            },
        )
    }

    fun onDeleteItem(id: String) {
        viewModelScope.launch {
            deleteHistoryItemUseCase(id).fold(
                onSuccess = {},
                onFailure = {
                    _uiState.update {
                        it.copy(userMessage = "No se pudo eliminar el registro")
                    }
                },
            )
        }
    }

    fun onShowClearConfirmation() {
        _uiState.update { it.copy(showClearConfirmation = true) }
    }

    fun onDismissClearConfirmation() {
        _uiState.update { it.copy(showClearConfirmation = false) }
    }

    fun onConfirmClearHistory() {
        viewModelScope.launch {
            _uiState.update { it.copy(showClearConfirmation = false) }
            clearHistoryUseCase().fold(
                onSuccess = {},
                onFailure = {
                    _uiState.update {
                        it.copy(userMessage = "No se pudo limpiar el historial")
                    }
                },
            )
        }
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }
}
