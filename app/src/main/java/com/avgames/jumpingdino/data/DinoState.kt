package com.avgames.jumpingdino.data

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.avgames.jumpingdino.presentation.earth_y_position
import com.avgames.jumpingdino.presentation.starting_point
import com.avgames.jumpingdino.ui.theme.DinoColor
import com.avgames.jumpingdino.utils.DinoPath
import com.avgames.jumpingdino.utils.DinoPath2

data class DinoState(
    var posX: Float = starting_point,
    var posY: Float = earth_y_position,
    var gravity: Float = 0f,
    val color: Color = DinoColor,
    var isJumping: Boolean = false,
    var velocity: Float = 0f,
    var pathList: ArrayList<Path> = arrayListOf(),
    var keyframe: Int = 0,
) {
    init {
        pathList.add(DinoPath())
        pathList.add(DinoPath2())
    }

    val width: Float
        get() = path.getBounds().width
    val height: Float
        get() = path.getBounds().height
    val jumpHeight: Float
        get() = earth_y_position - (height * gravity)
    val path: Path
        get() = if (keyframe <= 5) pathList[0] else pathList[1]
    val bounds: Rect
        get() = Rect(
            left = posX,
            top = posY - height,
            right = posX + width,
            bottom = posY
        )
}