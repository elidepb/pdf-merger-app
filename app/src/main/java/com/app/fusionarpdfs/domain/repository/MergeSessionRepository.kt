package com.app.fusionarpdfs.domain.repository

import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.PdfFileItem
import kotlinx.coroutines.flow.StateFlow

interface MergeSessionRepository {

    val selectedPdfs: StateFlow<List<PdfFileItem>>

    val mergeConfiguration: StateFlow<MergeConfiguration?>

    fun setSelectedPdfs(pdfs: List<PdfFileItem>)

    fun updatePdfOrder(pdfs: List<PdfFileItem>)

    fun removePdf(id: String)

    fun clearSelection()

    fun setMergeConfiguration(configuration: MergeConfiguration)

    fun clearSession()
}
