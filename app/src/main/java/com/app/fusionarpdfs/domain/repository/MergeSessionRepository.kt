package com.app.fusionarpdfs.domain.repository

import com.app.fusionarpdfs.domain.model.MergeConfiguration
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.model.PdfFileItem
import kotlinx.coroutines.flow.StateFlow

interface MergeSessionRepository {

    val selectedPdfs: StateFlow<List<PdfFileItem>>

    val mergeConfiguration: StateFlow<MergeConfiguration?>

    val lastMergeResult: StateFlow<MergeHistoryItem?>

    fun setSelectedPdfs(pdfs: List<PdfFileItem>)

    fun updatePdfOrder(pdfs: List<PdfFileItem>)

    fun removePdf(id: String)

    fun clearSelection()

    fun addPdfs(pdfs: List<PdfFileItem>)

    fun setMergeConfiguration(configuration: MergeConfiguration)

    fun setLastMergeResult(result: MergeHistoryItem?)

    fun clearSession()
}
