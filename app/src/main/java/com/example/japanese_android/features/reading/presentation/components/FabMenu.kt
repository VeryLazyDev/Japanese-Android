package com.example.japanese_android.features.reading.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FabMenu(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "icon_rotation"
    )
    FloatingActionButtonMenu(
        expanded = expanded,
        button = {
            ToggleFloatingActionButton(
                checked = expanded,
                onCheckedChange = { expanded = it },
                containerColor = ToggleFloatingActionButtonDefaults.containerColor(
                    MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.graphicsLayer {
                    // APPLY ROTATION HERE: This rotates the entire button content
                    rotationZ = rotation
                }

            ) {
                if (expanded)
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear",
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            // Apply the animated rotation to the icon
                            .graphicsLayer {
                                rotationZ = rotation
                            }
                            .padding(8.dp)
                    )
                else Text(
                    "N5",
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer {
                        // APPLY ROTATION HERE: This rotates the entire button content
                        rotationZ = rotation
                    }
                )
            }
        }
    ) { }
}