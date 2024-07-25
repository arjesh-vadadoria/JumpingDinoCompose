package com.avgames.jumpingdino.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avgames.jumpingdino.data.GameState
import com.avgames.jumpingdino.ui.theme.font

@Composable
fun ScoreView(
    modifier: Modifier,
    gameState: GameState,
) {
    Box(modifier) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .alpha(0.8f),
                    style = TextStyle.Default.copy(
                        fontSize = 18.sp,
                        fontFamily = font,
                        fontWeight = FontWeight(600),
                    ),
                    text = "High Score"
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .alpha(0.6f),
                    style = TextStyle.Default.copy(
                        fontSize = 18.sp,
                        fontFamily = font,
                        fontWeight = FontWeight(300),
                    ),
                    text = gameState.highScore.toString(),
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .alpha(0.8f),
                    style = TextStyle.Default.copy(
                        fontSize = 18.sp,
                        fontFamily = font,
                        fontWeight = FontWeight(600),
                    ),
                    text = "Current Score"
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .alpha(0.6f),
                    style = TextStyle.Default.copy(
                        fontSize = 18.sp,
                        fontFamily = font,
                        fontWeight = FontWeight(300),
                    ),
                    text = gameState.currentScore.toString(),
                )
            }
        }
    }
}