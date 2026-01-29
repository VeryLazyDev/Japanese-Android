package com.example.japanese_android.ui.theme
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = Color.White, // Add this
    onSurface = Color.White,    // Add this
    secondary = PurpleGrey80,
    tertiary = Pink80,
    outline = DarkOutline,
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    background = LightBackground,
    surface = LightSurface,
    onBackground = Color(0xFF1C1B1F), // Add this (Dark Grey/Black)
    onSurface = Color(0xFF1C1B1F),    // Add this
    secondary = PurpleGrey40,
    tertiary = Pink40,
    outline = LightOutline
)
//private val DarkColorScheme = darkColorScheme(
//    primary = DarkPrimary,
//    background = DarkBackground,
//    surface = DarkSurface,
//    secondary = PurpleGrey80,
//    tertiary = Pink80,
//    outline = DarkOutline,
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = LightPrimary,
//    background = LightBackground,
//    surface = LightSurface,
//    secondary = PurpleGrey40,
//    tertiary = Pink40,
//    outline = LightOutline
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)

@OptIn(ExperimentalAnimationSpecApi::class)
@Composable
fun JapaneseAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    //for theme switch
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}