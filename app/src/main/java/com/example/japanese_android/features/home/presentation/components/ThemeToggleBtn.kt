package com.example.japanese_android.features.home.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow

@Composable
fun ThemeToggleButton(
    isDarkMode: Boolean,
    isToggleEnabled: Boolean,
    onToggle: (Offset) -> Unit,
    animate: Boolean = true // Added to prevent double animation
) {
    val targetRotation = if (isDarkMode) 180f else 0f
    var centerOffset by remember { mutableStateOf(Offset.Zero) }

    val rotation by animateFloatAsState(
        targetValue = targetRotation,
        animationSpec = if (animate) spring(Spring.DampingRatioMediumBouncy)
        else  tween(durationMillis = 0),//spring(stiffness = 10000f), // Instant if not animating
        label = "icon_rotate"
    )

    IconButton (
        enabled = isToggleEnabled,
        onClick = { (onToggle(centerOffset)) },
        modifier = Modifier
            .graphicsLayer { rotationZ = rotation }
            .onGloballyPositioned { croods ->
                val position = croods.positionInWindow()
                val size = croods.size
                centerOffset = Offset(
                    x = position.x + size.width / 2f,
                    y = position.y + size.height / 2f
                )
            }
    ) {
        Icon(
            imageVector = if (isDarkMode) Icons.Rounded.DarkMode else Icons.Rounded.LightMode,
            contentDescription = "Toggle Theme",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}