import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import com.example.japanese_android.features.home.presentation.components.HomeContent
import com.example.japanese_android.ui.state.calculateScrollDirection
import com.example.japanese_android.ui.theme.JapaneseAndroidTheme

// 1. The Clip Shape Logic
class CircleClip(private val center: Offset, private val radius: Float) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            addOval(Rect(center, radius))
        }
        return Outline.Generic(path)
    }
}

@Composable
fun HomeScreen(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit,
    navController: NavController
) {
    // Stores where the user tapped
    var revealCenter by remember { mutableStateOf<Offset?>(null) }

    // This controls the radius animation
    // 3000f is usually enough to cover any phone screen size
    val animatedRadius by animateFloatAsState(
        targetValue = if (revealCenter != null) 3000f else 0f,
        animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing),
        finishedListener = {
            if (revealCenter != null) {
                // IMPORTANT: Only toggle the real global theme when animation finishes
                onThemeToggle()
                revealCenter = null
            }
        },
        label = "RevealAnimation"
    )

    val baseScrollState = rememberLazyListState()
    val overlayScrollState = rememberLazyListState()
    val isScrollUp = calculateScrollDirection(baseScrollState)

    val hasPlayedEntryAnimation = rememberSaveable { mutableStateOf(false) }
    val entryVisibleState = remember {
        MutableTransitionState(hasPlayedEntryAnimation.value)
            .apply { targetState = true }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🔹 BASE LAYER: Shows the CURRENT global theme
        JapaneseAndroidTheme(darkTheme = isDarkTheme) {
            HomeContent(
                scrollState = baseScrollState,
                isScrollUp = isScrollUp,
                isDark = isDarkTheme,
                entryVisibleState = entryVisibleState,
                iconDarkPreview = isDarkTheme,
                onToggleRequest = { offset ->
                    // Trigger the animation by setting the center point
                    revealCenter = offset
                },
                onEntryShown = { hasPlayedEntryAnimation.value = true },
                isAnimatingTheme = revealCenter != null,
                navController = navController
            )
        }

        // 🔹 OVERLAY LAYER: Shows the NEXT theme (Inverse of current)
        if (revealCenter != null) {
            JapaneseAndroidTheme(darkTheme = !isDarkTheme) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleClip(revealCenter!!, animatedRadius))
                ) {
                    HomeContent(
                        scrollState = overlayScrollState,
                        isScrollUp = isScrollUp,
                        isDark = !isDarkTheme,
                        iconDarkPreview = !isDarkTheme,
                        entryVisibleState = entryVisibleState,
                        onToggleRequest = {}, // Disable clicks on the overlay
                        onEntryShown = {},
                        isAnimatingTheme = true,
                        navController = navController
                    )
                }
            }
        }
    }
}