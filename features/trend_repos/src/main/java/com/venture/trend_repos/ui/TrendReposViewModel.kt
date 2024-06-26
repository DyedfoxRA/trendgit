package com.venture.trend_repos.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.core.domain.results.BaseError
import com.venture.core.domain.results.ResultResponse
import com.venture.network.model.DateRange
import com.venture.trend_repos.domain.model.Repo
import com.venture.trend_repos.domain.repos.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrendReposViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    private val _trendingRepos =
        MutableStateFlow<ResultResponse<List<Repo>, BaseError>>(ResultResponse.Idle)
    val trendingRepos: StateFlow<ResultResponse<List<Repo>, BaseError>> = _trendingRepos

    private val _language = MutableStateFlow("Any")
    val language: StateFlow<String> = _language

    private val _dateRange = MutableStateFlow(DateRange.WEEK)
    val dateRange: StateFlow<DateRange> = _dateRange

    init {
        fetchTrendingRepos()
    }

    fun updateLanguage(newLanguage: String) {
        _language.value = newLanguage
    }

    fun updateDateRange(newDateRange: DateRange) {
        _dateRange.value = newDateRange
        fetchTrendingRepos()
    }

    fun fetchTrendingRepos() {
        viewModelScope.launch {
            repository.getTrendingRepos(_language.value, _dateRange.value).collect { result ->
                _trendingRepos.value = result
            }
        }
    }
}
