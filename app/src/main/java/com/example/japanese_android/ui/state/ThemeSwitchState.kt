package com.example.japanese_android.ui.state

import androidx.compose.ui.geometry.Offset

data class ThemeSwitchState(
    val isDark: Boolean = false,
    val revealCenter: Offset? = null,
    val  revealRadius: Float = 0f
)
