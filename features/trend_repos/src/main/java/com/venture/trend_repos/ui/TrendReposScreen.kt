package com.venture.trend_repos.ui

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
fun TrendReposScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Trending Repositories", style = MaterialTheme.typography.headlineMedium)
        // Add content for trending repositories
    }
}

@Preview(showBackground = true)
@Composable
fun TrendReposScreenPreview() {
    TrendReposScreen()
}