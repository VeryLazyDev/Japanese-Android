package com.example.japanese_android.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.japanese_android.R
import com.example.japanese_android.common.components.AppLogo
import com.example.japanese_android.common.components.AppName
import com.example.japanese_android.presentation.components.FloatingTopBar
import com.example.japanese_android.ui.theme.HorizonFont

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .statusBarsPadding()
    ) {
        Column() {
            FloatingTopBar(title = "MUDA-ZERO", onMenuClick = {})
            Box(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                ) {
                    // WELCOME HEADER SECTION
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Welcome to ",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        AppLogo()
                        Spacer(modifier = Modifier.weight(.1f))
                        AppName(style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Text("Ready to Start your journey? Pick a module below to master your fluency.")
                }
            }
        }
    }
}