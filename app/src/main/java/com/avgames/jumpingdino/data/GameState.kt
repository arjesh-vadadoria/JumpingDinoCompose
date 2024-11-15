package com.avgames.jumpingdino.data

data class GameState(
    val dinoState: DinoState,
    var cactusState: CactusState,
    var earthState: EarthState,
    var isGameOver: Boolean,
    var isIntro: Boolean = false,
    var highScore: Int,
    var currentScore: Int,
)
