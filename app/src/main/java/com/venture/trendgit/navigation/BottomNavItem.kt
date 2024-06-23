package com.venture.trendgit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Favorite : BottomNavItem("favorite", Icons.Default.Favorite, "Favorite")
    data object Trend : BottomNavItem("trend", Icons.Default.Star, "Trend")
    data object Settings : BottomNavItem("settings", Icons.Default.Settings, "Settings")
    companion object {
        fun values(): List<BottomNavItem> {
            return listOf(Favorite, Trend, Settings)
        }
    }
}