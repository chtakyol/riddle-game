package com.oolong.riddle_game.ui.screen.game_screen

sealed class GameScreenEvent {
    data class KeyboardInput(val value: String): GameScreenEvent()
    object EnterClicked: GameScreenEvent()
    object RemoveClicked: GameScreenEvent()
    object PassClicked: GameScreenEvent()
    object PlayClicked: GameScreenEvent()
}
