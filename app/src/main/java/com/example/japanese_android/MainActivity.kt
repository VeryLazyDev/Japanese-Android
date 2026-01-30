package com.example.japanese_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.japanese_android.features.home.presentation.components.ThemeToggleButton
import com.example.japanese_android.features.home.presentation.screen.HomeScreen
import com.example.japanese_android.navigation.AppNavGraph
import com.example.japanese_android.ui.theme.JapaneseAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var isDark by remember { mutableStateOf(false) }
            val navController = rememberNavController()

            JapaneseAndroidTheme(darkTheme = isDark) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(
                        navController = navController,
                        isDarkTheme = isDark,
                        onThemeToggle = { isDark = !isDark }
                    )
                }
            }
        }
    }
}
