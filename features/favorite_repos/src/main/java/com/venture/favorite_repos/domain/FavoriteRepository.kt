package com.venture.favorite_repos.domain

import com.venture.core.domain.model.Repo


interface FavoriteRepository {

    suspend fun getAllRepos(): List<Repo>

    suspend fun insertRepo(repo: Repo)

    suspend fun deleteRepo(repo: Repo)
}