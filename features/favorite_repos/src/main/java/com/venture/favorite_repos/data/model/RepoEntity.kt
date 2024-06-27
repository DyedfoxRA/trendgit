package com.venture.favorite_repos.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val htmlUrl: String,
    val stargazersCount: Int,
    val language: String?,
    @Embedded val owner: OwnerEntity,
    val forksCount: Int
)