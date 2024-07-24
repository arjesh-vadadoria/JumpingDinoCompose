package com.avgames.jumpingdino.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope


fun DrawScope.EarthView() {
    drawLine(
        start = Offset(0f, earth_y_position),
        end = Offset(deviceWidthInPixels.toFloat(), earth_y_position),
        color = Color.Black,
        strokeWidth = EARTH_GROUND_STROKE_WIDTH,
    )
}