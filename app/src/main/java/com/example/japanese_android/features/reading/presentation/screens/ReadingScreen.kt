package com.example.japanese_android.features.reading.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.japanese_android.common.components.TextHeadLine
import kotlinx.coroutines.delay

@Composable
fun ReadingScreen(
    navController: NavController
) {
    var isTitleVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(700)
        isTitleVisible = true
    }
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .safeContentPadding()
            .fillMaxSize()
    ) {
        Column() {
//            if (isTitleVisible) {
//            }
            val itemVisible =
                remember { MutableTransitionState(false).apply { targetState = true } }
            AnimatedVisibility(
                visibleState = itemVisible,
                enter = fadeIn(tween(1600)) +
                        scaleIn(
                            initialScale = 0.8f,
                            animationSpec = tween(1500)
                        )
            ){
                TextHeadLine("Learning Modules")

            }
        }
    }
}