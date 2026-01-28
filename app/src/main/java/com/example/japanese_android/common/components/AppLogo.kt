package com.example.japanese_android.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.japanese_android.R

@Composable
fun AppLogo(height : Dp = 40.dp){
    Image(
        modifier = Modifier.height(height),
        painter = painterResource(R.drawable.logo),
        contentDescription = "APP_LOGO"
    )
}