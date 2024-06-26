package com.venture.trend_repos.domain.repos

import com.venture.core.domain.results.BaseError
import com.venture.core.domain.results.ResultResponse
import com.venture.network.model.DateRange
import com.venture.trend_repos.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getTrendingRepos(
        language: String,
        dateRange: DateRange
    ): Flow<ResultResponse<List<Repo>, BaseError>>
}