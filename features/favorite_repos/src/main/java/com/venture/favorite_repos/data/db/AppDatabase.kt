package com.venture.favorite_repos.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.venture.favorite_repos.data.RepoDao
import com.venture.favorite_repos.data.model.RepoEntity

@Database(entities = [RepoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}