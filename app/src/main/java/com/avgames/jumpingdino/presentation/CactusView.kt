package com.avgames.jumpingdino.presentation

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import com.avgames.jumpingdino.data.CactusState

fun DrawScope.CactusView(cactusList: CactusState) {
    for (cactus in cactusList.cactusList) {
        withTransform({
//            scale(scaleX = cactus.scale, scaleY = cactus.scale)
            translate(
                left = cactus.posX,
                top = cactus.posY - cactus.height
            )
        }) {
            drawRect(
                size = Size(
                    cactus.width,
                    cactus.height,
                ),
                color = cactus.color,
            )
        }
        drawBoundingBox(Color.Red, cactus.bounds)
    }
}