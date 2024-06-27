package com.venture.trendgit.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.venture.favorite_repos.ui.FavoriteReposScreen
import com.venture.settings.ui.SettingsScreen
import com.venture.trend_repos.ui.RepoDetailsScreen
import com.venture.trend_repos.ui.TrendReposScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Trend.route, modifier = modifier) {
        composable(
            BottomNavItem.Favorite.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            FavoriteReposScreen()
        }
        composable(
            BottomNavItem.Trend.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            TrendReposScreen(navController = navController)
        }
        composable(
            BottomNavItem.Settings.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            SettingsScreen()
        }
        composable(
            "repoDetails/{repoId}/{repoName}/{ownerLogin}",
            arguments = listOf(
                navArgument("repoId") { type = NavType.LongType },
                navArgument("repoName") { type = NavType.StringType },
                navArgument("ownerLogin") { type = NavType.StringType }
            ),
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) { backStackEntry ->
            val repoId = backStackEntry.arguments?.getLong("repoId") ?: 0L
            val repoName = backStackEntry.arguments?.getString("repoName") ?: ""
            val ownerLogin = backStackEntry.arguments?.getString("ownerLogin") ?: ""
            RepoDetailsScreen(navController = navController,repoId, repoName, ownerLogin)
        }
    }
}