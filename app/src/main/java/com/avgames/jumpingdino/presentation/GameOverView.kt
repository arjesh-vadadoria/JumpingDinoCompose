package com.avgames.jumpingdino.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GameOverView(
    modifier: Modifier,
    isGameOver: Boolean
) {
    if (isGameOver) {
        Box(modifier) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Game Over"
            )
        }
    }
}