package com.venture.network.services

import com.venture.network.model.GitSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): GitSearchResponse
}

