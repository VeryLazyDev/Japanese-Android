package com.example.japanese_android.features.home.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.japanese_android.common.components.AppLogo
import com.example.japanese_android.common.components.AppName
import com.example.japanese_android.common.components.TextHeadLine
import com.example.japanese_android.features.home.data.model.HomePageModules
import com.example.japanese_android.features.home.presentation.components.FloatingTopBar
import com.example.japanese_android.features.home.presentation.components.LearningModuleCard

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()

    val visibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .statusBarsPadding()
    ) {
        Column {
            FloatingTopBar(title = "MUDA-ZERO", onMenuClick = {})
            AnimatedVisibility(
                visibleState = visibleState,
                enter = fadeIn(animationSpec = tween(0)) +
                        slideInVertically(initialOffsetY = {0})
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 10.dp)
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                    ) {
                        // WELCOME HEADER SECTION
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextHeadLine("Welcome to ")
                            AppLogo()
                            Spacer(modifier = Modifier.weight(.1f))
                            AppName(style = MaterialTheme.typography.headlineSmall)
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text("Ready to Start your journey? Pick a module below to master your fluency.")

                        Spacer(modifier = Modifier.height(40.dp))

                        // LEARNING MODULES
                        TextHeadLine("Learning Modules")
                        Spacer(modifier = Modifier.height(10.dp))

                        HomePageModules.forEachIndexed { index, module ->
                            AnimatedVisibility(
                                visibleState = visibleState,
                                enter = fadeIn(animationSpec = tween(durationMillis = 800, delayMillis = 120 * index)) +
                                        scaleIn(initialScale = 0.95f, animationSpec = tween(durationMillis = 800, delayMillis = 140 * index))
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
                    }
                }
            }
        }
    }
}