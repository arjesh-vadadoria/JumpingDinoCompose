package com.avgames.jumpingdino.presentation

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import com.avgames.jumpingdino.data.DinoState

fun DrawScope.DinoView(dinoState: DinoState) {
    withTransform({
        translate(
            left = dinoState.posX,
            top = dinoState.posY - dinoState.height,
        )
    }) {
        drawPath(
            path = dinoState.path,
            color = dinoState.color,
            style = Fill
        )
    }
    drawBoundingBox(Color.Blue, dinoState.bounds)
}