package com.example.japanese_android.features.home.presentation.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavController
import com.example.japanese_android.features.home.presentation.components.HomeContent
import com.example.japanese_android.features.home.utils.CircleClip
import com.example.japanese_android.ui.state.calculateScrollDirection
import com.example.japanese_android.ui.theme.JapaneseAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    navController: NavController
) {
    // to control toggle click
    var isThemeTransitionRunning by remember { mutableStateOf(false) }

    var previewDarkTheme by remember { mutableStateOf(isDarkTheme) }


    val baseScrollState = rememberLazyListState()
    val overlayScrollState = rememberLazyListState()

    val isScrollUp = calculateScrollDirection(baseScrollState)

    val hasPlayedEntryAnimation = rememberSaveable { mutableStateOf(false) }
    val entryVisibleState = remember {
        MutableTransitionState(hasPlayedEntryAnimation.value).apply {
            targetState = true
        }
    }

    var revealCenter by remember { mutableStateOf<Offset?>(null) }
    var themeAtStartOfAnimation by remember { mutableStateOf(isDarkTheme) }

    val isAnimatingTheme = revealCenter != null
    val animatedRadius by animateFloatAsState(
        targetValue = if (revealCenter != null) 3000f else 0f,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "reveal radius",
        finishedListener = {
            if (revealCenter != null) {
                onThemeToggle()
                revealCenter = null
                isThemeTransitionRunning = false
                // sync icon with real theme
//                previewDarkTheme = isDarkTheme
            }
        }
    )

    // Stop scroll during animation
    LaunchedEffect(isAnimatingTheme) {
        if (isAnimatingTheme) baseScrollState.stopScroll()
    }


    Box(modifier = Modifier.fillMaxSize()) {
        // LAYER 1: Base (This layer handles ALL scroll input)
        val baseTheme = if (revealCenter != null) themeAtStartOfAnimation else isDarkTheme
        JapaneseAndroidTheme(darkTheme = baseTheme) {
            HomeContent(
                scrollState = baseScrollState,
                isScrollUp = isScrollUp,
                isDark = baseTheme,
                entryVisibleState = entryVisibleState,
                iconDarkPreview = previewDarkTheme,
                onToggleRequest = { offset ->
                    if (isThemeTransitionRunning) return@HomeContent
                    themeAtStartOfAnimation = isDarkTheme
                    revealCenter = offset
                    previewDarkTheme = !previewDarkTheme
                },
                onEntryShown = { hasPlayedEntryAnimation.value = true },
                isAnimatingTheme = revealCenter != null,
                navController = navController
            )
        }


        // LAYER 2: Overlay (Visual only - No touch input)
        if (revealCenter != null) {
            JapaneseAndroidTheme(darkTheme = !themeAtStartOfAnimation) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleClip(revealCenter!!, animatedRadius))
                        // CRITICAL: This prevents the overlay from stealing scroll focus
                        .graphicsLayer{ alpha = 0.99f }
                ) {
                    // We render the content but we disable interaction
                    Box(modifier = Modifier.fillMaxSize()) {
                        HomeContent(
                            scrollState = overlayScrollState,
                            isScrollUp = isScrollUp,
                            isDark = !themeAtStartOfAnimation,
                            iconDarkPreview = previewDarkTheme,
                            entryVisibleState = entryVisibleState,
                            onToggleRequest = {},
                            onEntryShown = { },
                            isAnimatingTheme = true,
                            navController = navController
                        )
                        // This transparent box catches clicks but lets the Base Layer
                        // underneath handle the actual scrolling logic
                        Box(modifier = Modifier.fillMaxSize().background(Color.Transparent))
                    }
                }
            }
        }
    }
}
