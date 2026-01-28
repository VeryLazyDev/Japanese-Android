package com.example.japanese_android.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.example.japanese_android.ui.theme.AuxMono

@Composable
fun TextTitle(
    title: String,
    style: TextStyle = MaterialTheme.typography.titleMedium
){
    Text(
        title,
        fontFamily = AuxMono,
        style = style
    )
}