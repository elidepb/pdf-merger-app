package com.app.fusionarpdfs.domain.usecase

import com.app.fusionarpdfs.domain.repository.MergeHistoryRepository
import com.app.fusionarpdfs.testutil.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SaveHistoryUseCaseTest {

    private val mergeHistoryRepository: MergeHistoryRepository = mockk()
    private lateinit var useCase: SaveHistoryUseCase

    @Before
    fun setUp() {
        useCase = SaveHistoryUseCase(mergeHistoryRepository)
    }

    @Test
    fun invoke_delegatesToRepository() = runTest {
        val item = TestData.mergeHistoryItem()
        coEvery { mergeHistoryRepository.save(item) } returns Result.success(Unit)

        val result = useCase(item)

        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { mergeHistoryRepository.save(item) }
    }

    @Test
    fun invoke_returnsRepositoryFailure() = runTest {
        val item = TestData.mergeHistoryItem()
        val error = RuntimeException("No se pudo guardar")
        coEvery { mergeHistoryRepository.save(item) } returns Result.failure(error)

        val result = useCase(item)

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }
}
