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
import com.avgames.jumpingdino.data.EarthModel
import com.avgames.jumpingdino.data.EarthState
import com.avgames.jumpingdino.data.GameState
import com.avgames.jumpingdino.presentation.CACTUS_COUNT
import com.avgames.jumpingdino.presentation.CACTUS_SPEED
import com.avgames.jumpingdino.presentation.DOUBT_FACTOR
import com.avgames.jumpingdino.presentation.EARTH_OFFSET
import com.avgames.jumpingdino.presentation.EARTH_SPEED
import com.avgames.jumpingdino.presentation.FRAME_DELAY
import com.avgames.jumpingdino.presentation.MAX_EARTH_BLOCKS
import com.avgames.jumpingdino.presentation.deviceWidthInPixels
import com.avgames.jumpingdino.presentation.distance_between_cactus
import com.avgames.jumpingdino.presentation.earth_y_position
import com.avgames.jumpingdino.presentation.event.GameEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SceneViewModel : ViewModel() {

    var gameState by mutableStateOf(
        GameState(
            dinoState = DinoState(),
            cactusState = CactusState(arrayListOf()),
            earthState = EarthState(),
            isGameOver = false,
            isIntro = true,
            highScore = 0,
            currentScore = 0,
        )
    )
        private set

    private val additionalCactusDistance: Int
        get() = (-(distance_between_cactus * 0.05).toInt()..(distance_between_cactus * 0.4).toInt()).random()

    fun onEvent(event: GameEvent) {
        when (event) {
            GameEvent.INTRO -> {
                gameState.cactusState.cactusList.clear()
                gameState.earthState.blockList.clear()

                var startX = deviceWidthInPixels.toFloat() - 100f
                for (i in 0..CACTUS_COUNT) {
                    gameState.cactusState.cactusList.add(
                        CactusModel(
                            scale = (85..130).random().toFloat() / 100f,
                            posX = startX,
                        )
                    )
                    startX += distance_between_cactus
                    startX += additionalCactusDistance
                }

                var earthStartX = -EARTH_OFFSET.toFloat()
                for (i in 0 until MAX_EARTH_BLOCKS) {
                    var earth = EarthModel(
                        posX = earthStartX,
                        posY = earth_y_position + (20 + i * 10),
                        size = deviceWidthInPixels + (EARTH_OFFSET * 2).toFloat(),
                    )

                    gameState.earthState.blockList.add(earth)
                    earthStartX += earth.size
                }
            }

            GameEvent.START_GAME -> {
                viewModelScope.launch {
                    gameState = GameState(
                        dinoState = DinoState(),
                        cactusState = CactusState(arrayListOf()),
                        earthState = EarthState(),
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

                        gameState.dinoState.posY += gameState.dinoState.velocity
                        gameState.dinoState.velocity += gameState.dinoState.gravity

                        if (gameState.dinoState.posY > earth_y_position) {
                            gameState.dinoState.posY = earth_y_position
                            gameState.dinoState.velocity = 0f
                            gameState.dinoState.gravity = 0f
                            gameState.dinoState.isJumping = false
                        }

                        val newList =
                            ArrayList(gameState.cactusState.cactusList.map { it.copy(posX = it.posX - CACTUS_SPEED) })
                        if (newList.isNotEmpty()) {
                            if (newList.first().posX <= -(newList.first().width + 50)) {
                                newList.removeFirst()
                                newList.add(
                                    CactusModel(
                                        scale = (85..130).random().toFloat() / 100f,
                                        posX = newList.last().posX + distance_between_cactus + additionalCactusDistance
                                    )
                                )
                            }
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
                            val endPos =
                                gameState.earthState.blockList[MAX_EARTH_BLOCKS - 1].posX + gameState.earthState.blockList[MAX_EARTH_BLOCKS - 1].size
                            for (i in 0 until MAX_EARTH_BLOCKS) {
                                val block = gameState.earthState.blockList[i]
                                block.posX -= EARTH_SPEED
                                if ((block.posX + block.size) < -EARTH_OFFSET) {
                                    block.posX = endPos
                                }
                            }
                            if (!gameState.dinoState.isJumping) {
                                if (gameState.dinoState.keyframe >= 10) {
                                    gameState.dinoState.keyframe = 0
                                } else {
                                    gameState.dinoState.keyframe += 1
                                }
                            }
                            val cactusState = gameState.cactusState.copy(cactusList = newList)
                            gameState.currentScore += 1
                            gameState = gameState.copy(
                                cactusState = cactusState,
                            )
                        }
                    }
                }
            }

            GameEvent.JUMP -> {
                viewModelScope.launch {
                    if (gameState.dinoState.posY == earth_y_position) {
                        gameState = gameState.copy(
                            dinoState = gameState.dinoState.copy(
                                isJumping = true,
                                velocity = -40f,
                                gravity = 2.5f
                            )
                        )
                    }
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