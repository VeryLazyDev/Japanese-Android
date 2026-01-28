package com.example.japanese_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.japanese_android.features.home.presentation.screen.HomeScreen
import com.example.japanese_android.ui.theme.JapaneseAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseAndroidTheme {
                HomeScreen()
            }
        }
    }
}
