package com.venture.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.venture.network.services.GitHubApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get<OkHttpClient>())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    single<GitHubApi> {
        get<Retrofit>().create(GitHubApi::class.java)
    }
}
