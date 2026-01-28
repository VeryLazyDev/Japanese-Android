package com.example.japanese_android.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.japanese_android.R
import com.example.japanese_android.ui.theme.AuxMono
import com.example.japanese_android.ui.theme.HorizonFont

@Composable
fun AppName(
    title : String = stringResource(R.string.app_name),
    style: TextStyle = MaterialTheme.typography.titleMedium
){
    Text(
        text = title,
        fontFamily = AuxMono,
        style = style,
        color = Color.Black
    )
}