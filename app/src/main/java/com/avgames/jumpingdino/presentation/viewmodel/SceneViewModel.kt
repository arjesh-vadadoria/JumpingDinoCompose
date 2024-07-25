package com.avgames.jumpingdino.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avgames.jumpingdino.data.CactusModel
import com.avgames.jumpingdino.data.CactusState
import com.avgames.jumpingdino.data.DinoState
import com.avgames.jumpingdino.data.GameState
import com.avgames.jumpingdino.presentation.CACTUS_COUNT
import com.avgames.jumpingdino.presentation.CACTUS_SPEED
import com.avgames.jumpingdino.presentation.DOUBT_FACTOR
import com.avgames.jumpingdino.presentation.FRAME_DELAY
import com.avgames.jumpingdino.presentation.deviceWidthInPixels
import com.avgames.jumpingdino.presentation.distance_between_cactus
import com.avgames.jumpingdino.presentation.event.GameEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SceneViewModel : ViewModel() {

    var gameState by mutableStateOf(
        GameState(
            dinoState = DinoState(),
            cactusState = CactusState(arrayListOf()),
            isGameOver = false,
            isIntro = true,
            highScore = 0,
            currentScore = 0,
        )
    )
        private set

    private val additionalCactusDistance: Int
        get() = (-(distance_between_cactus * 0.1).toInt()..(distance_between_cactus * 0.3).toInt()).random()

    fun onEvent(event: GameEvent) {
        when (event) {
            GameEvent.INTRO -> {
                gameState.cactusState.cactusList.clear()
                var startX = deviceWidthInPixels.toFloat() - 100f
                for (i in 0..CACTUS_COUNT) {
                    gameState.cactusState.cactusList.add(
                        CactusModel(
                            scale = (85..130).random().toFloat() / 100f,
                            posX = startX,
                        )
                    )
                    startX += (distance_between_cactus + (-300..300).random())
                    startX += additionalCactusDistance
                }
            }

            GameEvent.START_GAME -> {
                viewModelScope.launch {
                    gameState = GameState(
                        dinoState = DinoState(),
                        cactusState = CactusState(arrayListOf()),
                        isGameOver = false,
                        isIntro = false,
                        highScore = gameState.highScore,
                        currentScore = 0,
                    )
                    onEvent(GameEvent.INTRO)
                    onEvent(GameEvent.MOVE_CACTUS)
                }
            }

            GameEvent.MOVE_CACTUS -> {
                viewModelScope.launch {
                    while (!gameState.isGameOver) {
                        delay(FRAME_DELAY)
                        val newList =
                            ArrayList(gameState.cactusState.cactusList.map { it.copy(posX = it.posX - CACTUS_SPEED) })
                        if (newList.isNotEmpty()) {
                            if (newList.first().posX <= -newList.first().width) {
                                newList.removeFirst()
                                newList.add(
                                    CactusModel(
                                        scale = (85..130).random().toFloat() / 100f,
                                        posX = newList.last().posX + distance_between_cactus + additionalCactusDistance
                                    )
                                )
                            }
                            val cactusState = gameState.cactusState.copy(cactusList = newList)
                            gameState.currentScore += 1
                            gameState = gameState.copy(
                                cactusState = cactusState,
                            )
                            if (gameState.dinoState.bounds
                                    .deflate(DOUBT_FACTOR)
                                    .overlaps(
                                        gameState.cactusState.cactusList.first().bounds
                                            .deflate(DOUBT_FACTOR)
                                    )
                            ) {
                                Log.e("lucifer", "GameScene: BOOOOM !!!!!")
                                onEvent(GameEvent.GAME_OVER)
                                return@launch
                            }
                        }
                    }
                }
            }

            GameEvent.JUMP -> {
                viewModelScope.launch {
                    val dinoState =
                        if (gameState.dinoState.posY == gameState.dinoState.jumpHeight) {
                            gameState.dinoState.copy(isJumping = false)
                        } else {
                            gameState.dinoState.copy(isJumping = true)
                        }
                    gameState = gameState.copy(dinoState = dinoState)
                }
            }

            GameEvent.GAME_OVER -> {
                viewModelScope.launch {
                    if (gameState.currentScore > gameState.highScore) {
                        gameState.highScore = gameState.currentScore
                    }
                    gameState = gameState.copy(
                        isGameOver = true,
                        currentScore = 0,
                        highScore = gameState.highScore,
                    )
                }
            }
        }
    }

}