package com.avgames.jumpingdino.data

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import com.avgames.jumpingdino.presentation.earth_y_position
import com.avgames.jumpingdino.presentation.starting_point

data class DinoState(
    val width: Float = 80f,
    val height: Float = 130f,
    var posX: Float = starting_point,
    var posY: Float = earth_y_position,
    var gravity: Float = 2.5f,
    var jumpHeight: Float = earth_y_position - (height * gravity),
    val color: Color = Color.Gray,
    var isJumping: Boolean = false
) {
    val bounds: Rect
        get() = Rect(
            left = posX,
            top = posY - height,
            right = posX + width,
            bottom = posY
        )
}