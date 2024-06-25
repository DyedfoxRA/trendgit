package com.venture.trend_repos.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venture.network.model.RepoEntity
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrendReposScreen(viewModel: TrendReposViewModel = koinViewModel()) {
    val reposState = viewModel.trendingRepos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Trending Repositories", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(reposState.value.size) { index ->
                RepoItem(repoEntity = reposState.value[index]) {
                    //on click(
                }
            }
        }
    }
}

@Composable
fun RepoItem(repoEntity: RepoEntity, onClick: (RepoEntity) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(repoEntity) }
        .padding(8.dp)) {
        Text(text = repoEntity.name, style = MaterialTheme.typography.bodyLarge)
        repoEntity.description?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Text(text = "Language: ${repoEntity.language}", style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun TrendReposScreenPreview() {
    TrendReposScreen()
}