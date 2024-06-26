package com.venture.trend_repos.data.repos

import com.venture.core.domain.results.BaseError
import com.venture.core.domain.results.DataError
import com.venture.core.domain.results.ResultResponse
import com.venture.network.model.DateRange
import com.venture.network.services.GitHubApi
import com.venture.trend_repos.domain.mappers.RepoEntityToRepoMapper
import com.venture.trend_repos.domain.model.Repo
import com.venture.trend_repos.domain.repos.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class SearchRepositoryImpl(
    private val api: GitHubApi,
    private val repoMapper: RepoEntityToRepoMapper
) : SearchRepository {
    override suspend fun getTrendingRepos(
        language: String,
        dateRange: DateRange
    ): Flow<ResultResponse<List<Repo>, BaseError>> = flow {
        emit(ResultResponse.Loading)
        try {
            val query = buildQuery(language, dateRange)
            val response = api.searchRepositories(query)
            if (response.isSuccessful && response.body() != null) {
                val repoEntities = response.body()?.items ?: emptyList()
                val repos = repoMapper.mapList(repoEntities)
                emit(ResultResponse.Success(repos))
            } else {
                emit(ResultResponse.Error(DataError.Network.UNKNOWN))
            }
        } catch (e: HttpException) {
            emit(
                when (e.code()) {
                    408 -> ResultResponse.Error(DataError.Network.REQUEST_TIMEOUT)
                    413 -> ResultResponse.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                    else -> ResultResponse.Error(DataError.Network.UNKNOWN)
                }
            )
        } catch (e: Exception) {
            emit(ResultResponse.Error(DataError.Network.UNKNOWN))
        }
    }

    private fun buildQuery(language: String, dateRange: DateRange): String {
        val date = dateRange.getDateRange()
        return "language:$language created:>$date"
    }
}
