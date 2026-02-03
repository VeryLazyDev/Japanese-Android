package com.example.japanese_android.features.reading.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.japanese_android.common.constant.JapaneseLevel
import com.example.japanese_android.common.data.japaneseLevelDataList

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FabMenu(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    var expanded by remember { mutableStateOf(false) }
    var selectedLevel by remember { mutableStateOf(JapaneseLevel.N5.toString()) }
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
//                    MaterialTheme.colorScheme.surface
                    if (expanded) MaterialTheme.colorScheme.primary.copy(alpha = .1f) else MaterialTheme.colorScheme.primary
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
                    selectedLevel,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer {
                        // APPLY ROTATION HERE: This rotates the entire button content
                        rotationZ = rotation
                    }
                )
            }
        }
    ) {
        AnimatedVisibility(
            visible = expanded,
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .size(width = 60.dp, height = 90.dp)
                    .background(/*MaterialTheme.colorScheme.secondary.copy(alpha = .4f)*/
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = .3.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(15.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(japaneseLevelDataList) { index, item ->
                    val scale by remember {
                        derivedStateOf {
                            val layoutInfo = listState.layoutInfo
                            val visibleItemsInfo = layoutInfo.visibleItemsInfo
                            val itemInfo = visibleItemsInfo.find { it.index == index }

                            if (itemInfo != null) {
                                // Calculate center of the viewport
                                val viewportCenter = layoutInfo.viewportEndOffset / 2f
                                // Calculate center of the item
                                val itemCenter = itemInfo.offset + (itemInfo.size / 2f)
                                // Calculate distance from center (normalized 0.0 to 1.0)
                                val distanceFromCenter = Math.abs(viewportCenter - itemCenter)
                                val normalizedDistance =
                                    (distanceFromCenter / viewportCenter).coerceIn(0f, 1f)

                                1f - (normalizedDistance * 0.7f) // Scale factor
                            } else {
                                0.3f // Default scale for non-visible items
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                alpha = scale // Fade out as it scales down
                            }
                            .clickable(
                                onClick = {
                                    selectedLevel = item.level.toString()
                                    expanded = false
                                }
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.level.toString(),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}