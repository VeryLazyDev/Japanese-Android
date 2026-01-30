package com.example.japanese_android.common.data;

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesomeMosaic
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import com.example.japanese_android.R
import com.example.japanese_android.common.data.model.BottomNavItem
import com.example.japanese_android.common.data.model.NavIcon
import com.example.japanese_android.navigation.Routes

val bottomNavItems = listOf(
    BottomNavItem(
        route = Routes.Home.route,
        label = "Home",
        icon = NavIcon.Vector(Icons.Rounded.AutoAwesomeMosaic)
    ),
    BottomNavItem(
        route = Routes.HIRAGANA.route,
        label = "Language",
        icon = NavIcon.Png(R.drawable.hiragana_a)
    ),
    BottomNavItem(
        route = Routes.Search.route,
        label = "Search",
        icon = NavIcon.Vector(Icons.Rounded.Search)
    ),
    BottomNavItem(
        route = Routes.Settings.route,
        label = "Settings",
        icon = NavIcon.Vector(Icons.Rounded.Settings)
    )
)
