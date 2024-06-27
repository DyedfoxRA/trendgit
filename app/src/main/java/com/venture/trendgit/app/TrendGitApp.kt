package com.venture.trendgit.app

import android.app.Application
import com.venture.favorite_repos.di.favoriteModule
import com.venture.network.di.networkModule
import com.venture.settings.di.settingsModule
import com.venture.trend_repos.di.trendModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrendGitApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TrendGitApp)
            modules(
                networkModule,
                settingsModule,
                trendModule,
                favoriteModule,
            )
        }
    }
}