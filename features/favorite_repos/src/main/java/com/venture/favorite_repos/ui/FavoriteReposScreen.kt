package com.venture.favorite_repos.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.venture.core.ui.DisplayResult
import com.venture.core.ui.EmptyState
import com.venture.core.ui.ErrorState
import com.venture.core.ui.RepoItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteReposScreen(viewModel: FavoriteReposViewModel = koinViewModel()) {
    val favoriteRepos by viewModel.favoriteRepos.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Favorite Repositories", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        favoriteRepos.DisplayResult(
            onIdle = { /* Do nothing */ },
            onLoading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            onSuccess = { repos ->
                if (repos.isEmpty()) {
                    EmptyState(message = "No favorite repositories found")
                } else {
                    LazyColumn {
                        items(repos.size) { index ->
                            RepoItem(
                                repo = repos[index],
                                isFavorite = true,
                                onClick = { /* Handle repo click */ },
                                onAddFavorite = { viewModel.addFavoriteRepo(repos[index]) },
                                onRemoveFavorite = { viewModel.removeFavoriteRepo(repos[index]) }
                            )
                        }
                    }
                }
            },
            onError = { error ->
                ErrorState(
                    error = error,
                    onRetry = { viewModel.loadFavoriteRepos() },
                    onBack = null
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FavoriteReposScreenPreview() {
    FavoriteReposScreen()
}