package com.venture.settings.di

import com.venture.settings.ui.ThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { ThemeViewModel() }
}