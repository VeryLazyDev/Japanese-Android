package com.example.japanese_android.common.data.model

import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavIcon {
    data class Vector(val icon: ImageVector) : NavIcon()
    data class Png(val resId: Int) : NavIcon()
}