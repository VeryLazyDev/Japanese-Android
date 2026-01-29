package com.example.japanese_android.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    background = DarkBackground,
    surface = DarkSurface,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    outline = DarkOutline,
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    background = LightBackground,
    surface = LightSurface,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    outline = LightOutline

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JapaneseAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }

    //for theme switch
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val animationBg by animateColorAsState(
        targetValue = colorScheme.background,
        animationSpec = tween(durationMillis = 10), label = "bg_anim"
    )
    val animationSurface by animateColorAsState(
        targetValue = colorScheme.surface,
        animationSpec = tween(durationMillis = 10), label = "bg_anim"

    )
    val animatedPrimary by animateColorAsState(
        targetValue = colorScheme.primary,
        animationSpec = tween(durationMillis = 10), label = "primary_anim"
    )

    val  animationColorShceme = colorScheme.copy(
        background = animationBg,
        surface = animationSurface,
        primary = animatedPrimary
    )

    MaterialTheme(
        colorScheme = animationColorShceme,
        typography = Typography,
        content = content
    )
}