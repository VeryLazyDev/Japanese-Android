package com.example.japanese_android.features.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.japanese_android.common.components.AppLogo
import com.example.japanese_android.common.components.AppName
import com.example.japanese_android.common.components.bottom_nav.BottomNavBar
import com.example.japanese_android.common.components.TextHeadLine
import com.example.japanese_android.features.home.data.model.HomePageModules

@Composable
fun HomeContent(
    scrollState: LazyListState,
    isScrollUp: Boolean,
    isDark: Boolean,
    entryVisibleState: MutableTransitionState<Boolean>,
    iconDarkPreview: Boolean,
    onToggleRequest: (Offset) -> Unit,
    onEntryShown: () -> Unit,
    isAnimatingTheme: Boolean,
    navController: NavController
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    LaunchedEffect(entryVisibleState.currentState) {
        if (entryVisibleState.currentState) {
            onEntryShown()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FloatingTopBar(
                title = "MUDA-ZERO",
                isDarkTheme = iconDarkPreview,
                animateIcon = !isAnimatingTheme,
                isToggleEnabled = !isAnimatingTheme,
                onThemeToggle = { offset ->
                    onToggleRequest(offset)
                }
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
                        visible = entryVisibleState.targetState && !isAnimatingTheme,
                        enter = fadeIn(tween(500)) + slideInVertically(initialOffsetY = { 40 }),
                        exit = fadeOut()
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(30.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                TextHeadLine("Welcome to ", color = textColor)
                                AppLogo()
                                Spacer(modifier = Modifier.weight(.1f))
                                AppName(
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = textColor
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                "Ready to Start your journey? Pick a module below to master your fluency.",
                                color = textColor
                            )
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
                        val itemVisible =
                            remember { MutableTransitionState(false).apply { targetState = true } }
                        AnimatedVisibility(
                            visibleState = itemVisible,
                            enter = fadeIn(tween(800, delayMillis = 120 * index)) +
                                    scaleIn(
                                        initialScale = 0.95f,
                                        animationSpec = tween(800, delayMillis = 140 * index)
                                    )
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
            BottomNavBar(
                navController = navController
            )
        }
    }
}