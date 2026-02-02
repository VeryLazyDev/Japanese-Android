package com.example.japanese_android.features.setting.presentation.screen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.japanese_android.common.components.TextHeadLine
import com.example.japanese_android.common.components.bottom_nav.BottomNavBar

@Composable
fun SettingScreen(
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
                enter = fadeIn(tween(800)) +
                        scaleIn(
                            initialScale = 0.8f,
                            animationSpec = tween(700)
                        )
            ) {
                TextHeadLine("Setting")
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
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            BottomNavBar(
                navController = navController
            )
        }
    }
}