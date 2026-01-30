package com.example.japanese_android.features.reading.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.japanese_android.common.components.TextHeadLine
import com.example.japanese_android.common.components.bottom_nav.BottomNavBar
import kotlinx.coroutines.delay

@Composable
fun ReadingScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
//            .safeContentPadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            val itemVisible =
                remember { MutableTransitionState(false).apply { targetState = true } }
            AnimatedVisibility(
                visibleState = itemVisible,
                enter = fadeIn(tween(1600)) +
                        scaleIn(
                            initialScale = 0.8f,
                            animationSpec = tween(1500)
                        )
            ) {
                TextHeadLine("Learning Modules")
            }

        Spacer(modifier = Modifier.weight(1f))
        }
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            BottomNavBar(
                navController = navController
            )
        }
    }
}