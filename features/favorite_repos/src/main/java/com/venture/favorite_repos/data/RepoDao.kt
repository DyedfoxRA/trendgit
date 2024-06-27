package com.venture.favorite_repos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.venture.favorite_repos.data.model.RepoEntity

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos")
    suspend fun getAllRepos(): List<RepoEntity>

    @Insert
    suspend fun insertRepo(repo: RepoEntity)

    @Delete
    suspend fun deleteRepo(repo: RepoEntity)
}