package com.avgames.jumpingdino.presentation

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawBoundingBox(color: Color, rect: Rect, name: String? = null) {
    if (showBounds) {
        drawRect(color, rect.topLeft, rect.size, style = Stroke(3f))
        drawRect(
            color,
            rect.deflate(DOUBT_FACTOR).topLeft,
            rect.deflate(DOUBT_FACTOR).size,
            style = Stroke(
                width = 3f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(2f, 4f), 0f)
            )
        )
    }
}

