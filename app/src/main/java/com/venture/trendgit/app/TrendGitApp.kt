package com.venture.trendgit.app

import android.app.Application
import com.venture.network.di.networkModule
import com.venture.settings.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrendGitApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TrendGitApp)
            modules(
                networkModule,
                settingsModule
            )
        }
    }
}