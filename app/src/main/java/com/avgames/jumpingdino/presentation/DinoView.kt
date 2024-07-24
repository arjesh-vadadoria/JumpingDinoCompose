package com.avgames.jumpingdino.presentation

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import com.avgames.jumpingdino.data.DinoState

fun DrawScope.DinoView(dinoState: DinoState) {
    withTransform({
        translate(
            left = dinoState.posX,
            top = dinoState.posY - dinoState.height,
        )
    }) {
        drawRect(
            size = Size(
                dinoState.width,
                dinoState.height,
            ),
            color = dinoState.color,
        )
    }
    drawBoundingBox(Color.Blue, dinoState.bounds)
}