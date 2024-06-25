package com.venture.trend_repos.domain.repos

import com.venture.network.model.DateRange
import com.venture.trend_repos.domain.model.Repo

interface SearchRepository {
    suspend fun getTrendingRepos(
        language: String,
        dateRange: DateRange
    ): List<Repo>
}