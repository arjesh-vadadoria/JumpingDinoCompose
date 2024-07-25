package com.avgames.jumpingdino.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.avgames.jumpingdino.ui.theme.font

@Composable
fun IntroView(
    modifier: Modifier,
    isIntro: Boolean
) {
    if (isIntro) {
        Box(modifier) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .alpha(0.8f),
                    style = TextStyle.Default.copy(
                        fontSize = 30.sp,
                        fontFamily = font,
                        fontWeight = FontWeight(600),
                    ),
                    text = "Start Game",
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .alpha(0.6f),
                    style = TextStyle.Default.copy(
                        fontSize = 20.sp,
                        fontFamily = font,
                        fontWeight = FontWeight(300),
                    ),
                    text = "Tap to start game",
                )
            }
        }
    }
}