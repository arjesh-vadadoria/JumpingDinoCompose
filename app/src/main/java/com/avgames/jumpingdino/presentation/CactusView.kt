package com.avgames.jumpingdino.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import com.avgames.jumpingdino.data.CactusState

fun DrawScope.CactusView(cactusList: CactusState) {
    for (cactus in cactusList.cactusList) {
        withTransform({
//            scale(scaleX = cactus.scale, scaleY = cactus.scale)
            translate(
                left = cactus.posX,
                top = (cactus.posY - cactus.height)
            )
        }) {
            drawPath(
                path = cactus.path,
                color = cactus.color,
                style = Fill
            )
        }
        drawBoundingBox(Color.Red, cactus.bounds)
    }
}