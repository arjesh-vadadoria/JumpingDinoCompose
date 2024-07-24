package com.avgames.jumpingdino.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Size {

    val height: Int
        @Composable
        get() {
            val configuration = LocalConfiguration.current
            return configuration.screenHeightDp
        }

    val heightPx: Int
        @Composable
        get() {
            val density = LocalDensity.current
            return with(density) { height.dp.roundToPx() }
        }

    val width: Int
        @Composable
        get() {
            val configuration = LocalConfiguration.current
            return configuration.screenWidthDp
        }

    val widthPx: Int
        @Composable
        get() {
            val density = LocalDensity.current
            return with(density) { width.dp.roundToPx() }
        }

}