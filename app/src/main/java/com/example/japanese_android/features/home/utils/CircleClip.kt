package com.example.japanese_android.features.home.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CircleClip(
    private val center: Offset,
    private val radius: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            // Create bounding Rect for the oval (circle in this case)
            val ovalRect = Rect(
                left = center.x - radius,
                top = center.y - radius,
                right = center.x + radius,
                bottom = center.y + radius
            )
            addOval(ovalRect)
            // Optional: you can specify direction if needed (default is CounterClockwise)
            // addOval(ovalRect, Path.Direction.Clockwise)
        }
        return Outline.Generic(path)
    }
}