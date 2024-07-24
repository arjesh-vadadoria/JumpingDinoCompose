package com.avgames.jumpingdino.presentation.event

sealed class GameEvent {
    data object INTRO : GameEvent()
    data object START_GAME : GameEvent()
    data object MOVE_CACTUS : GameEvent()
    data object JUMP : GameEvent()
    data object GAME_OVER : GameEvent()
}