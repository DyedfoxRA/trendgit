package com.venture.favorite_repos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venture.core.ui.RepoItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteReposScreen(viewModel: FavoriteReposViewModel = koinViewModel()) {
    val favoriteRepos by viewModel.favoriteRepos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Favorite Repositories", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        if (favoriteRepos.isEmpty()) {
            EmptyState()
        } else {
            LazyColumn {
                items(favoriteRepos.size) { index ->
                    com.venture.core.ui.RepoItem(repo = favoriteRepos[index]) {
                        viewModel.removeFavoriteRepo(favoriteRepos[index])
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No favorite repositories found")
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteReposScreenPreview() {
    FavoriteReposScreen()
}