package com.avgames.jumpingdino.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.avgames.jumpingdino.data.EarthState


fun DrawScope.EarthView(earthState: EarthState) {
    drawLine(
        start = Offset(0f, earth_y_position),
        end = Offset(deviceWidthInPixels.toFloat(), earth_y_position),
        color = Color.Black,
        strokeWidth = EARTH_GROUND_STROKE_WIDTH,
    )
    earthState.blockList.forEach { block ->
        drawLine(
            color = earthState.color,
            start = Offset(x = block.posX, y = earth_y_position + 20),
            end = Offset(x = block.size, y = earth_y_position + 20),
            strokeWidth = EARTH_GROUND_STROKE_WIDTH / 5,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 40f), 0f)
        )
        drawLine(
            color = earthState.color,
            start = Offset(x = block.posX, y = earth_y_position + 30),
            end = Offset(x = block.size, y = earth_y_position + 30),
            strokeWidth = EARTH_GROUND_STROKE_WIDTH / 5,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 50f), 40f)
        )
    }
}