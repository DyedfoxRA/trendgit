package com.venture.favorite_repos.domain

import com.venture.core.domain.model.Repo
import com.venture.core.domain.results.DataError
import com.venture.core.domain.results.ResultResponse
import kotlinx.coroutines.flow.Flow


interface FavoriteRepository {

    suspend fun getAllRepos(): Flow<ResultResponse<List<Repo>, DataError>>
    suspend fun insertRepo(repo: Repo): Flow<ResultResponse<Unit, DataError>>
    suspend fun deleteRepo(repo: Repo): Flow<ResultResponse<Unit, DataError>>
}