package com.example.japanese_android.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.japanese_android.R
import com.example.japanese_android.common.components.AppLogo
import com.example.japanese_android.common.components.AppName
import com.example.japanese_android.ui.theme.HorizonFont

@Composable
fun FloatingTopBar(
    title: String, onMenuClick: () -> Unit
) {
    val cardShape = RoundedCornerShape(13.dp)

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .shadow(
                elevation = 3.dp,
                shape = cardShape,
//                ambientColor = Color.Blue,
                spotColor = Color.Gray
            )
            .fillMaxWidth(),
        shape = cardShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // APP LOGO
            AppLogo()
            // SPACE
            Spacer(modifier = Modifier.weight(.1f))

            // APP NAME
            AppName()

            // SPACE
            Spacer(modifier = Modifier.weight(1.5f))

            // MENU ICON
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "HOME_MENU"
                )
            }

        }
    }
}