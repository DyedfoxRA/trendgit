package com.venture.favorite_repos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.core.domain.model.Repo
import com.venture.core.domain.results.DataError
import com.venture.core.domain.results.ResultResponse
import com.venture.favorite_repos.domain.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteReposViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _favoriteRepos =
        MutableStateFlow<ResultResponse<List<Repo>, DataError>>(ResultResponse.Idle)
    val favoriteRepos: StateFlow<ResultResponse<List<Repo>, DataError>> = _favoriteRepos
    val favRepos: StateFlow<List<Repo>> = favoriteRepos
        .map { result ->
            if (result is ResultResponse.Success) {
                result.data
            } else {
                emptyList()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadFavoriteRepos()
    }

    fun loadFavoriteRepos() {
        viewModelScope.launch {
            repository.getAllRepos().collect {
                _favoriteRepos.value = it
            }
        }
    }

    fun addFavoriteRepo(repo: Repo) {
        viewModelScope.launch {
            repository.insertRepo(repo).collect {
                if (it is ResultResponse.Success) {
                    loadFavoriteRepos()
                }
            }
        }
    }

    fun removeFavoriteRepo(repo: Repo) {
        viewModelScope.launch {
            repository.deleteRepo(repo).collect {
                if (it is ResultResponse.Success) {
                    loadFavoriteRepos()
                }
            }
        }
    }
}
