package com.venture.trend_repos.data.repos

import com.venture.network.model.DateRange
import com.venture.network.model.RepoEntity
import com.venture.network.services.GitHubApi
import com.venture.trend_repos.domain.mappers.RepoEntityToRepoMapper
import com.venture.trend_repos.domain.model.Repo
import com.venture.trend_repos.domain.repos.SearchRepository

class SearchRepositoryImpl(
    private val api: GitHubApi,
    private val repoMapper: RepoEntityToRepoMapper
) : SearchRepository {
    override suspend fun getTrendingRepos(
        language: String,
        dateRange: DateRange
    ): List<Repo> {
        val query = buildQuery(language, dateRange)
        val repoEntities = api.searchRepositories(query).items
        return  repoMapper.mapList(repoEntities)
    }

    private fun buildQuery(language: String, dateRange: DateRange): String {
        val date = dateRange.getDateRange()
        return "language:$language created:>$date"
    }
}