package com.venture.favorite_repos.data.repo

import com.venture.favorite_repos.data.RepoDao
import com.venture.favorite_repos.domain.FavoriteRepository
import com.venture.favorite_repos.domain.mapper.RepoEntityToRepoMapper
import com.venture.favorite_repos.domain.mapper.RepoToRepoEntityMapper
import com.venture.trend_repos.domain.model.Repo

class FavoriteRepositoryImpl(
    private val repoDao: RepoDao,
    private val entityToRepoMapper: RepoEntityToRepoMapper,
    private val repoToEntityMapper: RepoToRepoEntityMapper
) : FavoriteRepository {

    override suspend fun getAllRepos(): List<Repo> {
        return entityToRepoMapper.mapList(repoDao.getAllRepos())
    }

    override suspend fun insertRepo(repo: Repo) {
        repoDao.insertRepo(repoToEntityMapper.map(repo))
    }

    override suspend fun deleteRepo(repo: Repo) {
        repoDao.deleteRepo(repoToEntityMapper.map(repo))
    }
}