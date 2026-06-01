package com.app.fusionarpdfs.data.repository

import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.PdfFileItem
import com.app.fusionarpdfs.domain.repository.MergeSessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MergeSessionRepositoryImpl @Inject constructor() : MergeSessionRepository {

    private val _selectedPdfs = MutableStateFlow<List<PdfFileItem>>(emptyList())
    override val selectedPdfs: StateFlow<List<PdfFileItem>> = _selectedPdfs.asStateFlow()

    private val _mergeConfiguration = MutableStateFlow<MergeConfiguration?>(null)
    override val mergeConfiguration: StateFlow<MergeConfiguration?> = _mergeConfiguration.asStateFlow()

    override fun setSelectedPdfs(pdfs: List<PdfFileItem>) {
        _selectedPdfs.value = pdfs.sortedBy { it.order }
    }

    override fun updatePdfOrder(pdfs: List<PdfFileItem>) {
        _selectedPdfs.value = pdfs.mapIndexed { index, pdf ->
            pdf.copy(order = index)
        }
    }

    override fun removePdf(id: String) {
        _selectedPdfs.update { pdfs ->
            pdfs
                .filterNot { it.id == id }
                .mapIndexed { index, pdf -> pdf.copy(order = index) }
        }
    }

    override fun clearSelection() {
        _selectedPdfs.value = emptyList()
    }

    override fun setMergeConfiguration(configuration: MergeConfiguration) {
        _mergeConfiguration.value = configuration
    }

    override fun clearSession() {
        _selectedPdfs.value = emptyList()
        _mergeConfiguration.value = null
    }
}
