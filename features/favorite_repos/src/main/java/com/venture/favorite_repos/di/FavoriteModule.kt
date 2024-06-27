package com.venture.favorite_repos.di

import androidx.room.Room
import com.venture.favorite_repos.data.db.AppDatabase
import com.venture.favorite_repos.data.repo.FavoriteRepositoryImpl
import com.venture.favorite_repos.domain.FavoriteRepository
import com.venture.favorite_repos.domain.mapper.RepoEntityToRepoMapper
import com.venture.favorite_repos.domain.mapper.RepoEntityToRepoMapperImpl
import com.venture.favorite_repos.domain.mapper.RepoToRepoEntityMapper
import com.venture.favorite_repos.domain.mapper.RepoToRepoEntityMapperImpl
import com.venture.favorite_repos.ui.FavoriteReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().repoDao() }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get(), get(), get()) }
    single<RepoEntityToRepoMapper> { RepoEntityToRepoMapperImpl() }
    single<RepoToRepoEntityMapper> { RepoToRepoEntityMapperImpl() }
    viewModel { FavoriteReposViewModel(get()) }
}