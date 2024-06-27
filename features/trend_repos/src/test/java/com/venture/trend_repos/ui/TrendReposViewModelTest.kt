package com.venture.trend_repos.ui

import com.venture.core.domain.model.Owner
import com.venture.core.domain.model.Repo
import com.venture.core.domain.results.DataError
import com.venture.core.domain.results.ResultResponse
import com.venture.network.model.DateRange
import com.venture.trend_repos.domain.repos.SearchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class TrendReposViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private val repository: SearchRepository = mockk()
    private lateinit var viewModel: TrendReposViewModel


    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TrendReposViewModel(repository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchTrendingRepos should update trendingRepos with success`() = runTest(testDispatcher) {
        val repoList = listOf(
            Repo(
                1,
                "repo1",
                "repo1/full",
                "Description 1",
                "htmlUrl1",
                100,
                "Kotlin",
                Owner("owner1", "avatarUrl1"),
                10
            ),
            Repo(
                2,
                "repo2",
                "repo2/full",
                "Description 2",
                "htmlUrl2",
                200,
                "Java",
                Owner("owner2", "avatarUrl2"),
                20
            )
        )

        coEvery { repository.getTrendingRepos(any(), any()) } returns flow {
            emit(ResultResponse.Success(repoList))
        }

        viewModel.fetchTrendingRepos()


        val result = viewModel.trendingRepos.value
        assertTrue(result is ResultResponse.Success)
        assertEquals(repoList, (result as ResultResponse.Success).data)
    }

    @Test
    fun `fetchTrendingRepos should update trendingRepos with error`() = runTest(testDispatcher) {
        coEvery { repository.getTrendingRepos(any(), any()) } returns flow {
            emit(ResultResponse.Error(DataError.Network.UNKNOWN))
        }

        viewModel.fetchTrendingRepos()


        val result = viewModel.trendingRepos.value
        assertTrue(result is ResultResponse.Error)
        assertEquals(DataError.Network.UNKNOWN, (result as ResultResponse.Error).error)
    }

    @Test
    fun `updateLanguage should update the language state`() {
        val newLanguage = "Java"
        viewModel.updateLanguage(newLanguage)
        assertEquals(newLanguage, viewModel.language.value)
    }

    @Test
    fun `updateDateRange should update the date range and fetch trending repos`() = runTest {
        val newDateRange = DateRange.MONTH
        coEvery { repository.getTrendingRepos(any(), newDateRange) } returns flow {
            emit(ResultResponse.Success(emptyList()))
        }

        viewModel.updateDateRange(newDateRange)

        advanceUntilIdle()

        assertEquals(newDateRange, viewModel.dateRange.value)
    }
}
