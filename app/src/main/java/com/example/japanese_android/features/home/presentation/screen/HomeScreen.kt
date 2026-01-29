package com.example.japanese_android.features.home.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.japanese_android.common.components.AppLogo
import com.example.japanese_android.common.components.AppName
import com.example.japanese_android.common.components.BottomNavBar
import com.example.japanese_android.common.components.TextHeadLine
import com.example.japanese_android.features.home.data.model.HomePageModules
import com.example.japanese_android.features.home.presentation.components.FloatingTopBar
import com.example.japanese_android.features.home.presentation.components.LearningModuleCard
import com.example.japanese_android.features.home.utils.CircleClip
import com.example.japanese_android.ui.state.calculateScrollDirection
import com.example.japanese_android.ui.theme.JapaneseAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    val baseScrollState = rememberLazyListState()
    val overlayScrollState = rememberLazyListState()

    val isScrollUp = calculateScrollDirection(baseScrollState)

    val entryVisibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }

    var revealCenter by remember { mutableStateOf<Offset?>(null) }
    var themeAtStartOfAnimation by remember { mutableStateOf(isDarkTheme) }

    val animatedRadius by animateFloatAsState(
        targetValue = if (revealCenter != null) 3000f else 0f,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "reveal radius",
        finishedListener = {
            if (revealCenter != null) {
                onThemeToggle()
                revealCenter = null
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // LAYER 1: Base (This layer handles ALL scroll input)
        val baseTheme = if (revealCenter != null) themeAtStartOfAnimation else isDarkTheme
        JapaneseAndroidTheme(darkTheme = baseTheme) {
            HomeContent(
                scrollState = baseScrollState,
                isScrollUp = isScrollUp,
                isDark = baseTheme,
                entryVisibleState = entryVisibleState,
                onToggleRequest = { offset ->
                    themeAtStartOfAnimation = isDarkTheme
                    revealCenter = offset
                },
                isAnimatingTheme = revealCenter != null
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
                            entryVisibleState = entryVisibleState,
                            onToggleRequest = {},
                            isAnimatingTheme = true
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

@Composable
fun HomeContent(
    scrollState: LazyListState,
    isScrollUp: Boolean,
    isDark: Boolean,
    entryVisibleState: MutableTransitionState<Boolean>,
    onToggleRequest: (Offset) -> Unit,
    isAnimatingTheme: Boolean
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FloatingTopBar(
                title = "MUDA-ZERO",
                isDarkTheme = isDark,
                animateIcon = !isAnimatingTheme,
                onThemeToggle = { onToggleRequest(Offset(900f, 150f)) }
            )

            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    // Only apply slide-in to the header on first load
                    AnimatedVisibility(
                        visibleState = entryVisibleState,
                        enter = fadeIn(tween(500)) + slideInVertically(initialOffsetY = { 40 })
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(30.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                TextHeadLine("Welcome to ", color = textColor)
                                AppLogo()
                                Spacer(modifier = Modifier.weight(.1f))
                                AppName(style = MaterialTheme.typography.headlineSmall, color = textColor)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("Ready to Start your journey? Pick a module below to master your fluency.", color = textColor)
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }
                }

                item {
                    TextHeadLine("Learning Modules", color = textColor)
                    Spacer(modifier = Modifier.height(10.dp))
                }

                itemsIndexed(
                    items = HomePageModules,
                    key = { _, module -> module.title } // Keeps scroll state synced
                ) { index, module ->

                    // IF we are animating the theme reveal, DO NOT run the scale animation
                    // This prevents the cards from "locking" or flickering
                    if (isAnimatingTheme) {
                        LearningModuleCard(
                            title = module.title,
                            desc = module.desc,
                            iconText = module.icon,
                            iconColor = module.color,
                            onClick = {}
                        )
                    } else {
                        // This only runs on first app entry
                        val itemVisible = remember { MutableTransitionState(false).apply { targetState = true } }
                        AnimatedVisibility(
                            visibleState = itemVisible,
                            enter = fadeIn(tween(800, delayMillis = 120 * index)) +
                                    scaleIn(initialScale = 0.95f, animationSpec = tween(800, delayMillis = 140 * index))
                        ) {
                            LearningModuleCard(
                                title = module.title,
                                desc = module.desc,
                                iconText = module.icon,
                                iconColor = module.color,
                                onClick = {}
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }

        // Bottom Navbar
        AnimatedVisibility(
            visible = (isScrollUp || scrollState.firstVisibleItemScrollOffset == 0) && !isAnimatingTheme,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomNavBar()
        }
    }
}