package com.app.fusionarpdfs.domain.usecase

import android.net.Uri
import com.app.fusionarpdfs.domain.model.MergeError
import com.app.fusionarpdfs.domain.model.MergeException
import com.app.fusionarpdfs.domain.model.MergeHistoryItem
import com.app.fusionarpdfs.domain.repository.PdfMergerRepository
import com.app.fusionarpdfs.testutil.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class MergePdfUseCaseTest {

    private val pdfMergerRepository: PdfMergerRepository = mockk()
    private lateinit var useCase: MergePdfUseCase

    @Before
    fun setUp() {
        useCase = MergePdfUseCase(pdfMergerRepository)
    }

    @Test
    fun invoke_returnsFailureWhenFilesAreEmpty() = runTest {
        val result = useCase(
            files = emptyList(),
            outputFileName = "salida.pdf",
            outputUri = TestData.pdfUri(),
        )

        assertTrue(result.isFailure)
        assertEquals(
            MergeError.EMPTY,
            (result.exceptionOrNull() as MergeException).error,
        )
    }

    @Test
    fun invoke_returnsFailureWhenOutputFileNameIsBlank() = runTest {
        val result = useCase(
            files = TestData.pdfItems(2),
            outputFileName = "   ",
            outputUri = TestData.pdfUri(),
        )

        assertTrue(result.isFailure)
        assertEquals(
            MergeError.INVALID_CONFIGURATION,
            (result.exceptionOrNull() as MergeException).error,
        )
    }

    @Test
    fun invoke_sortsFilesByOrderBeforeMerging() = runTest {
        val files = listOf(
            TestData.pdfItem(id = "b", order = 1),
            TestData.pdfItem(id = "a", order = 0),
        )
        val outputUri = TestData.pdfUri("salida.pdf")
        val filesSlot = slot<List<com.app.fusionarpdfs.domain.model.PdfFileItem>>()
        val expectedResult = TestData.mergeHistoryItem()

        coEvery {
            pdfMergerRepository.mergePdfs(
                files = capture(filesSlot),
                outputFileName = "salida.pdf",
                outputUri = outputUri,
                onProgress = any(),
            )
        } returns Result.success(expectedResult)

        val result = useCase(
            files = files,
            outputFileName = "salida.pdf",
            outputUri = outputUri,
        )

        assertTrue(result.isSuccess)
        assertEquals(expectedResult, result.getOrNull())
        assertEquals(listOf("a", "b"), filesSlot.captured.map { it.id })
        coVerify(exactly = 1) {
            pdfMergerRepository.mergePdfs(
                files = any(),
                outputFileName = "salida.pdf",
                outputUri = outputUri,
                onProgress = any(),
            )
        }
    }

    @Test
    fun invoke_forwardsProgressCallback() = runTest {
        val files = TestData.pdfItems(2)
        val outputUri = TestData.pdfUri("salida.pdf")
        val progressEvents = mutableListOf<Pair<Int, Int>>()

        coEvery {
            pdfMergerRepository.mergePdfs(
                files = any(),
                outputFileName = any(),
                outputUri = any(),
                onProgress = any(),
            )
        } coAnswers {
            @Suppress("UNCHECKED_CAST")
            val callback = invocation.args[3] as (Int, Int) -> Unit
            callback(1, 2)
            Result.success(TestData.mergeHistoryItem())
        }

        useCase(
            files = files,
            outputFileName = "salida.pdf",
            outputUri = outputUri,
            onProgress = { current, total -> progressEvents.add(current to total) },
        )

        assertEquals(listOf(1 to 2), progressEvents)
    }

    @Test
    fun invoke_returnsRepositoryFailure() = runTest {
        val files = TestData.pdfItems(2)
        val outputUri = TestData.pdfUri("salida.pdf")
        val exception = MergeException(MergeError.CORRUPT)

        coEvery {
            pdfMergerRepository.mergePdfs(
                files = any(),
                outputFileName = any(),
                outputUri = any(),
                onProgress = any(),
            )
        } returns Result.failure(exception)

        val result = useCase(
            files = files,
            outputFileName = "salida.pdf",
            outputUri = outputUri,
        )

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
