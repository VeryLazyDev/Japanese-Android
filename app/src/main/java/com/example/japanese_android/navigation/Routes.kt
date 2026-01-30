package com.example.japanese_android.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object HIRAGANA : Routes("hiragana")
    object Search : Routes("search")
    object Settings : Routes("setting")
}