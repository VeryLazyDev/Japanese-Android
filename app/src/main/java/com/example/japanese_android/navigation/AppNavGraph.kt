package com.example.japanese_android.navigation

import HomeScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.japanese_android.features.hiragana.presentation.screens.HiraganaScreen
import com.example.japanese_android.features.reading.presentation.screens.ReadingScreen
import com.example.japanese_android.features.search.presentation.screen.SearchScreen
import com.example.japanese_android.features.setting.presentation.screen.SettingScreen

@Composable
fun AppNavGraph(
    navController: NavHostController, isDarkTheme: Boolean, onThemeToggle: () -> Unit
) {
    NavHost(
        navController = navController, startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1000),
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(600)
            )
        }) {
            HomeScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle,
                navController = navController
            )
        }

        composable(
            Routes.HIRAGANA.route,
        ) {
            HiraganaScreen(
                navController = navController
            )
        }

        composable(Routes.Search.route) {
            SearchScreen(
                navController = navController
            )
        }

        composable(Routes.Settings.route) {
            SettingScreen(navController = navController)
        }

        // home page modules
        composable(
            Routes.Reading.route
        ) {
            ReadingScreen(navController = navController, isDarkTheme = isDarkTheme)
        }
    }
}

