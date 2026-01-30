package com.example.japanese_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.japanese_android.features.home.presentation.screen.HomeScreen
import com.example.japanese_android.features.reading.presentation.screens.ReadingScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle,
                navController = navController
            )
        }

        composable(Routes.HIRAGANA.route) {
//            LanguageScreen()
            ReadingScreen()
        }

        composable(Routes.Search.route) {
//            SearchScreen()
        }

        composable(Routes.Settings.route) {
//            SettingsScreen()
        }
    }
}

