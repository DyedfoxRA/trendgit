package com.venture.trend_repos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.venture.trend_repos.R
import com.venture.trend_repos.domain.model.Owner
import com.venture.trend_repos.domain.model.Repo

@Composable
fun RepoItem(repo: Repo, onClick: (Repo) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(repo) }
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = repo.fullName,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Blue
        )
        repo.description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LanguageBadge(language = repo.language)
            Spacer(modifier = Modifier.width(8.dp))

            IconWithText(icon = Icons.Default.Star, text = "${repo.stargazersCount}")
            Spacer(modifier = Modifier.width(8.dp))
            IconWithText(icon = Icons.Default.Share, text = "${repo.forksCount}")
            Spacer(modifier = Modifier.width(8.dp))
            Contributors(avatars = listOf(repo.owner.avatarUrl)) // Assuming you have a list of avatar URLs
        }
        Row(
            modifier = Modifier.padding(top = 8.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = repo.owner.avatarUrl),
                contentDescription = null,
                modifier = Modifier.size(24.dp).background(Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = repo.owner.login,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun LanguageBadge(language: String?) {
    if (language != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color.Blue, CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = language, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Composable
fun IconWithText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

@Composable
fun Contributors(avatars: List<String>) {
    Row {
        avatars.forEach { avatarUrl ->
            Image(
                painter = rememberAsyncImagePainter(model = avatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
                    .background(Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    RepoItem(repo = Repo(
        id = 1,
        name = "sample",
        fullName = "sample/repo",
        description = "This is a sample repository",
        htmlUrl = "https://github.com/sample/repo",
        stargazersCount = 2134,
        language = "Kotlin",
        owner = Owner("sampleOwner", "https://avatars.githubusercontent.com/u/1?v=4"),
        forksCount = 192
    ), onClick = {})
}