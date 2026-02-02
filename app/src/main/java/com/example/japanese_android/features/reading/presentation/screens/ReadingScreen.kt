package com.example.japanese_android.features.reading.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.scrollableArea
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.japanese_android.R
import com.example.japanese_android.common.components.TextHeadLine
import com.example.japanese_android.common.components.TextTitle
import com.example.japanese_android.features.home.presentation.components.LearningModuleCard
import com.example.japanese_android.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingScreen(
    navController: NavController, isDarkTheme: Boolean
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, orientation = Orientation.Vertical)
                .padding(horizontal = 15.dp)
        ) {
            val itemVisible =
                remember { MutableTransitionState(false).apply { targetState = true } }
            AnimatedVisibility(
                visibleState = itemVisible, enter = fadeIn(tween(800)) + scaleIn(
                    initialScale = 0.8f, animationSpec = tween(700)
                )
            ) {
                Row(
                    modifier = Modifier.height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate(Routes.Home.route)
                    }) {
                        Icon(
                            Icons.Default.ChevronLeft, contentDescription = "back icon"
                        )
                    }
                    TextHeadLine("Reading Modules")
                    Spacer(modifier = Modifier.weight(1f))
                    // show bottom sheet
                    IconButton(onClick = {
                        showBottomSheet = !showBottomSheet
                    }) {
                        Icon(
                            if (showBottomSheet) Icons.Rounded.Clear else Icons.Rounded.Menu,
                            contentDescription = "back icon"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // body section

            // jlpt reading card
            OutlinedCard(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(.5.dp, color = MaterialTheme.colorScheme.outline),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = .3.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                            .padding(horizontal = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painterResource(if (isDarkTheme) R.drawable.book_white else R.drawable.book_black),
                                contentDescription = "book",
                                modifier = Modifier.size(26.dp),
                            )
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = "KeyboardArrowRight"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        TextTitle(title = "JLPT READING")
                        Text(
                            text = "Strategically selected exercises to equip you with essential skills for the tests.",
                            fontSize = 13.sp
                        )
                    }
                }
            }
// quiz card
            OutlinedCard(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(.5.dp, color = MaterialTheme.colorScheme.outline),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = .3.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(25.dp)
                            .padding(horizontal = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painterResource(R.drawable.zip),
                                contentDescription = "book",
                                modifier = Modifier.size(26.dp),
                            )
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.ChevronRight,
                                    contentDescription = "KeyboardArrowRight"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        TextTitle(title = "SPEED RUN")
                        Text(
                            text = "Accelerate and sharpen your reading speed & accuracy by doing a quick comprehension blitz.",
                            fontSize = 13.sp
                        )
                    }
                }
            }



            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    }, sheetState = sheetState
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        StableWheelPicker()
                    }

//                    japaneseLevelDataList.forEach { level ->
//                        Card(
//                            modifier = Modifier
//                                .height(60.dp)
//                                .fillMaxWidth()
//                                .padding(vertical = 5.dp, horizontal = 15.dp)
//                                .background(MaterialTheme.colorScheme.surface),
//                        ) {
//                            Box (
//                                modifier = Modifier.fillMaxSize(),
//                                contentAlignment = Alignment.Center
//                            ){
//                                Row(
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.Center
//                                ) {
//                                    Text(
//                                        level.level.toString(),
//                                        textAlign = TextAlign.Center,
//                                        fontSize = 18.sp,
//                                        fontFamily = AuxMono,
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                }
//                            }
//                        }
//                    }
                }
            }
        }

    }
}

@Composable
fun StableWheelPicker() {
    val listState = rememberLazyListState()
    val items = (1..50).toList()

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .size(width = 200.dp, height = 250.dp)
                .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items) { index, item ->
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

                            1f - (normalizedDistance * 0.5f) // Scale factor
                        } else {
                            0.5f // Default scale for non-visible items
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            alpha = scale // Fade out as it scales down
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(text = "Item $item", style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}