package com.venture.trend_repos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.network.model.DateRange
import com.venture.trend_repos.domain.model.Repo
import com.venture.trend_repos.domain.repos.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrendReposViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    private val _trendingRepos = MutableStateFlow<List<Repo>>(emptyList())
    val trendingRepos: StateFlow<List<Repo>> = _trendingRepos

    fun fetchTrendingRepos(language: String, dateRange: DateRange) {
        viewModelScope.launch {
            try {
                val repos = repository.getTrendingRepos(language, dateRange)
                _trendingRepos.value = repos
            } catch (e: Exception) {
                // Handle other exceptions
                e.printStackTrace()
            }
        }
    }
}