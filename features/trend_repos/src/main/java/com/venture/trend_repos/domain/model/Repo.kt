package com.venture.trend_repos.domain.model

data class Repo(
    val id: Long,
    val name: String,
    val description: String?,
    val htmlUrl: String,
    val stargazersCount: Int,
    val owner: Owner
)