package com.venture.favorite_repos.data.repo

import com.venture.core.domain.model.Repo
import com.venture.core.domain.results.DataError
import com.venture.core.domain.results.ResultResponse
import com.venture.favorite_repos.data.RepoDao
import com.venture.favorite_repos.domain.FavoriteRepository
import com.venture.favorite_repos.domain.mapper.RepoEntityToRepoMapper
import com.venture.favorite_repos.domain.mapper.RepoToRepoEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.sql.SQLException

class FavoriteRepositoryImpl(
    private val repoDao: RepoDao,
    private val entityToRepoMapper: RepoEntityToRepoMapper,
    private val repoToEntityMapper: RepoToRepoEntityMapper
) : FavoriteRepository {

    override suspend fun getAllRepos(): Flow<ResultResponse<List<Repo>, DataError>> = flow {
        emit(ResultResponse.Loading)
        try {
            val repos = repoDao.getAllRepos()
            emit(ResultResponse.Success(entityToRepoMapper.mapList(repos)))
        } catch (e: IOException) {
            emit(ResultResponse.Error(DataError.Local.IO_ERROR))
        } catch (e: SQLException) {
            emit(ResultResponse.Error(DataError.Local.DATABASE_READ_ERROR))
        } catch (e: Exception) {
            emit(ResultResponse.Error(DataError.Common.UNKNOWN))
        }
    }

    override suspend fun insertRepo(repo: Repo): Flow<ResultResponse<Unit, DataError>> = flow {
        try {
            repoDao.insertRepo(repoToEntityMapper.map(repo))
            emit(ResultResponse.Success(Unit))
        } catch (e: IOException) {
            emit(ResultResponse.Error(DataError.Local.IO_ERROR))
        } catch (e: SQLException) {
            emit(ResultResponse.Error(DataError.Local.DATABASE_WRITE_ERROR))
        } catch (e: Exception) {
            emit(ResultResponse.Error(DataError.Common.UNKNOWN))
        }
    }

    override suspend fun deleteRepo(repo: Repo): Flow<ResultResponse<Unit, DataError>> = flow {
        try {
            repoDao.deleteRepo(repoToEntityMapper.map(repo))
            emit(ResultResponse.Success(Unit))
        } catch (e: IOException) {
            emit(ResultResponse.Error(DataError.Local.IO_ERROR))
        } catch (e: SQLException) {
            emit(ResultResponse.Error(DataError.Local.DATABASE_WRITE_ERROR))
        } catch (e: Exception) {
            emit(ResultResponse.Error(DataError.Common.UNKNOWN))
        }
    }
}
