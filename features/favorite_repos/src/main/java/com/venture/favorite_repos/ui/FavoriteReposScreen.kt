package com.venture.favorite_repos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteReposScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Favorite Repositories", style = MaterialTheme.typography.headlineMedium)
        // Add content for favorite repositories
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteReposScreenPreview() {
    FavoriteReposScreen()
}