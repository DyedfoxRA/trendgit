package com.venture.trend_repos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.venture.network.model.DateRange
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrendReposScreen(viewModel: TrendReposViewModel = koinViewModel()) {
    val reposState = viewModel.trendingRepos.collectAsState()

    val language by viewModel.language.collectAsStateWithLifecycle()
    val dateRange by viewModel.dateRange.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Trending Repositories", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            OutlinedTextField(
                value = language,
                onValueChange = { viewModel.updateLanguage(it) },
                singleLine = true,
                label = { Text("Language") },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 56.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { viewModel.fetchTrendingRepos() })
            )

            Box {
                OutlinedButton(
                    onClick = { expanded = true },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.heightIn(min = 56.dp)
                ) {
                    Text(text = dateRange.name)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DateRange.entries.forEach { range ->
                        DropdownMenuItem(
                            text = { Text(text = range.name) },
                            onClick = {
                                viewModel.updateDateRange(range)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        LazyColumn {
            items(reposState.value.size) { index ->
                RepoItem(repo = reposState.value[index]) {
                    //on click(
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TrendReposScreenPreview() {
    TrendReposScreen()
}