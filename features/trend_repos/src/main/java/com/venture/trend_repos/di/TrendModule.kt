package com.venture.trend_repos.di

import com.venture.network.services.GitHubApi
import com.venture.trend_repos.data.repos.SearchRepositoryImpl
import com.venture.trend_repos.domain.mappers.RepoDTORepoMapper
import com.venture.trend_repos.domain.mappers.RepoDTORepoMapperImpl
import com.venture.trend_repos.domain.repos.SearchRepository
import com.venture.trend_repos.ui.TrendReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trendModule = module {
    single<SearchRepository> { SearchRepositoryImpl(api = get<GitHubApi>(), repoMapper = get()) }
    viewModel { TrendReposViewModel(repository = get()) }
    factory<RepoDTORepoMapper> { RepoDTORepoMapperImpl() }
}