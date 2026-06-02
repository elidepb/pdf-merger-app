package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.model.PdfSelectionValidation
import com.app.fusionarpdfs.domain.model.ValidationError
import com.app.fusionarpdfs.testutil.TestData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ValidatePdfSelectionUseCaseTest {

    private lateinit var useCase: ValidatePdfSelectionUseCase

    @Before
    fun setUp() {
        useCase = ValidatePdfSelectionUseCase()
    }

    @Test
    fun invoke_returnsTooFewFilesWhenSelectionHasOnePdf() {
        val result = useCase(listOf(TestData.pdfItem()))

        assertEquals(
            PdfSelectionValidation.Invalid(ValidationError.TOO_FEW_FILES),
            result,
        )
    }

    @Test
    fun invoke_returnsTooFewFilesWhenSelectionIsEmpty() {
        val result = useCase(emptyList())

        assertEquals(
            PdfSelectionValidation.Invalid(ValidationError.TOO_FEW_FILES),
            result,
        )
    }

    @Test
    fun invoke_returnsDuplicateFilesWhenUrisRepeat() {
        val sharedUri = TestData.pdfUri("duplicado.pdf")
        val files = listOf(
            TestData.pdfItem(id = "1", uri = sharedUri, order = 0),
            TestData.pdfItem(id = "2", uri = sharedUri, order = 1),
        )

        val result = useCase(files)

        assertEquals(
            PdfSelectionValidation.Invalid(ValidationError.DUPLICATE_FILES),
            result,
        )
    }

    @Test
    fun invoke_returnsValidWhenAtLeastTwoUniquePdfs() {
        val files = TestData.pdfItems(2)

        val result = useCase(files)

        assertEquals(PdfSelectionValidation.Valid, result)
    }
}
