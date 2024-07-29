package com.avgames.jumpingdino.data

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.avgames.jumpingdino.presentation.earth_y_position
import com.avgames.jumpingdino.ui.theme.CactusColor
import com.avgames.jumpingdino.utils.CactusPath

data class CactusModel(
    val scale: Float = 1f,
    var posX: Float = 0f,
    val posY: Float = earth_y_position,
    val color: Color = CactusColor,
    var path: Path = CactusPath(),
) {
    val width: Float
        get() = path.getBounds().width
    val height: Float
        get() = path.getBounds().height
    val bounds: Rect
        get() = Rect(
            left = posX,
            top = posY - (height * scale),
            right = posX + (width * scale),
            bottom = posY
        )
}
