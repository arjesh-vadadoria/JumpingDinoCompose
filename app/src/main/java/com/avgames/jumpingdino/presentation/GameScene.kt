package com.avgames.jumpingdino.presentation

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avgames.jumpingdino.data.GameState
import com.avgames.jumpingdino.presentation.event.GameEvent

const val EARTH_GROUND_STROKE_WIDTH = 3f
const val EARTH_OFFSET = 200
const val EARTH_SPEED = 9
const val MAX_EARTH_BLOCKS = 2
val earth_y_position: Float
    get() = deviceHeightInPixels.toFloat() - 100f

val starting_point: Float
    get() = deviceWidthInPixels.toFloat() * 0.1f

const val CACTUS_COUNT = 3
var distance_between_cactus = 250f
const val CACTUS_SPEED = EARTH_SPEED
const val FRAME_DELAY = 8L
const val DOUBT_FACTOR = 20f

var deviceWidthInPixels = 1920
var deviceHeightInPixels = 1920

val showBounds = false

@Composable
fun GameScene(
    gameState: GameState,
    onEvent: (GameEvent) -> Unit
) {

    val jumpingValue by animateFloatAsState(
        targetValue = if (gameState.dinoState.isJumping) gameState.dinoState.jumpHeight else earth_y_position,
        animationSpec = tween(easing = EaseInOutSine),
        label = "jump"
    )

//    if (!gameState.isGameOver) {
//        gameState.dinoState.posY = jumpingValue
//        //jump to get down on earth
//        if (jumpingValue == gameState.dinoState.jumpHeight) {
//            onEvent(GameEvent.JUMP)
//        }
//    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                when {
                    !gameState.isGameOver && !gameState.isIntro -> {
                        onEvent(GameEvent.JUMP)
                    }

                    else -> {
                        onEvent(GameEvent.START_GAME)
                    }
                }
            }
    ) {
        Canvas(
            modifier = Modifier.weight(1f)
        ) {
            EarthView(earthState = gameState.earthState)
            DinoView(dinoState = gameState.dinoState)
            CactusView(cactusList = gameState.cactusState)
        }
    }
    ScoreView(
        modifier = Modifier
            .padding(
                vertical = 40.dp,
                horizontal = 20.dp
            ),
        gameState = gameState
    )
    IntroView(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        isIntro = gameState.isIntro
    )
    GameOverView(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        isGameOver = gameState.isGameOver
    )
}

