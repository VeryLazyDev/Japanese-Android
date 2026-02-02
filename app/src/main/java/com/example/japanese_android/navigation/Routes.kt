package com.example.japanese_android.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object HIRAGANA : Routes("hiragana")
    object Search : Routes("search")
    object Settings : Routes("setting")

    //home page modules routes
    object Reading : Routes("reading")
    object Kanji : Routes("kanji")
    object Grammar : Routes("grammar")
    object Listening : Routes("listening")
    object Vocabulary : Routes("vocabulary")

}