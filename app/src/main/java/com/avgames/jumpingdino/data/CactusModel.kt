package com.avgames.jumpingdino.data

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import com.avgames.jumpingdino.presentation.earth_y_position

data class CactusModel(
    val width: Float = 50f,
    val height: Float = 90f,
    val scale: Float = 1f,
    var posX: Float = 0f,
    val posY: Float = earth_y_position,
    val color: Color = Color.Green,
) {
    val bounds: Rect
        get() = Rect(
            left = posX,
            top = posY - height,
            right = posX + width,
            bottom = posY
        )
}
