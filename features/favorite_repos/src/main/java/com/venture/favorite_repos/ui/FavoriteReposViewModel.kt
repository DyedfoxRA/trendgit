package com.venture.favorite_repos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venture.favorite_repos.domain.FavoriteRepository
import com.venture.trend_repos.domain.model.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteReposViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _favoriteRepos = MutableStateFlow<List<Repo>>(emptyList())
    val favoriteRepos: StateFlow<List<Repo>> = _favoriteRepos

    init {
        loadFavoriteRepos()
    }

    fun loadFavoriteRepos() {
        viewModelScope.launch {
            _favoriteRepos.value = repository.getAllRepos()
        }
    }

    fun addFavoriteRepo(repo: Repo) {
        viewModelScope.launch {
            repository.insertRepo(repo)
            loadFavoriteRepos()
        }
    }

    fun removeFavoriteRepo(repo: Repo) {
        viewModelScope.launch {
            repository.deleteRepo(repo)
            loadFavoriteRepos()
        }
    }
}