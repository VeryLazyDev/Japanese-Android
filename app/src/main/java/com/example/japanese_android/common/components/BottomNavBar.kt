package com.example.japanese_android.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.AutoAwesomeMosaic
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp) // Lifted off the bottom
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(
                // The key is a very low alpha white
                containerColor = MaterialTheme.colorScheme.background
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            // Border adds the "sharp" glass edge look
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.background.copy(alpha = 0.8f)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Example Icons
                GlassNavItem(Icons.Rounded.AutoAwesomeMosaic, "Home", true)
                GlassNavItem(Icons.Rounded.Search, "Profile", false)
//                GlassNavItem(Icons.Rounded., "Profile", false)
                GlassNavItem(Icons.Rounded.Settings, "Lessons", false)
            }
        }
    }
}


@Composable
fun GlassNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        // This padding moves the whole "Pill" up and down
        modifier = Modifier.padding(vertical = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .height(32.dp)
                .width(52.dp)
                .clip(CircleShape) // The Oval shape
                .background(
                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    else Color.Transparent
                )
                .border(
                    width = 1.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    else Color.Transparent,
                    shape = CircleShape
                ),
//                .clickable(
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = null, // Removes the default grey ripple if you want it clean
//                    onClick = { /* Handle Click */ }
//                ),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
//
//@Composable
//fun GlassNavItem(icon: ImageVector, label: String, isSelected: Boolean) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.padding(
//            vertical = 10.dp
//        )
//    ) {
//        IconButton(
//            onClick = { },
//            modifier = Modifier
//                .height(32.dp)
//                .width(52.dp)
//                .clip(CircleShape) // Essential for the Oval look
//                .background(
//                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
//                    else Color.Transparent
//                )
//                .border(
//                    width = 1.dp, // Must be at least 1.dp to be visible
//                    color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
//                    else Color.Transparent,
//                    shape = CircleShape // Shape must match the clip
//                )
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = label,
//                tint = if (isSelected) MaterialTheme.colorScheme.primary
//                else MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
//                modifier = Modifier.size(24.dp)
//            )
//        }
//    }
//}