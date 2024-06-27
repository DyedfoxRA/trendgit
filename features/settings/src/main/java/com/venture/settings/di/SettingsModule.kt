package com.venture.settings.di

import com.venture.settings.ui.ThemeViewModel
import org.koin.dsl.module

val settingsModule = module {
    single { ThemeViewModel() }
}