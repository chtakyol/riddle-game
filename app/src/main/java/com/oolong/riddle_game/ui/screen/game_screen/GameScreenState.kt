package com.oolong.riddle_game.ui.screen.game_screen

import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.ui.component.letter_indicator.LetterIndicatorState

data class GameScreenState(
    val letterIndicatorState: MutableMap<String, LetterIndicatorState> = mutableMapOf(
        "a" to LetterIndicatorState.ACTIVE,
        "b" to LetterIndicatorState.PASSIVE,
        "c" to LetterIndicatorState.PASSIVE,
        "d" to LetterIndicatorState.PASSIVE,
        "e" to LetterIndicatorState.PASSIVE,
        "f" to LetterIndicatorState.PASSIVE,
        "g" to LetterIndicatorState.PASSIVE,
        "h" to LetterIndicatorState.PASSIVE,
        "i" to LetterIndicatorState.PASSIVE,
        "j" to LetterIndicatorState.PASSIVE,
        "k" to LetterIndicatorState.PASSIVE,
        "l" to LetterIndicatorState.PASSIVE,
        "m" to LetterIndicatorState.PASSIVE,
        "n" to LetterIndicatorState.PASSIVE,
        "o" to LetterIndicatorState.PASSIVE,
        "p" to LetterIndicatorState.PASSIVE,
        "q" to LetterIndicatorState.PASSIVE,
        "r" to LetterIndicatorState.PASSIVE,
        "s" to LetterIndicatorState.PASSIVE,
        "t" to LetterIndicatorState.PASSIVE,
        "u" to LetterIndicatorState.PASSIVE,
        "v" to LetterIndicatorState.PASSIVE,
        "w" to LetterIndicatorState.PASSIVE,
        "x" to LetterIndicatorState.PASSIVE,
        "y" to LetterIndicatorState.PASSIVE,
        "z" to LetterIndicatorState.PASSIVE
    ),
    val index: Int = 0,
    val quizData: List<QuizDataEntity>? = null,
    val answer: String = "",
    val remainingTime: Long? = null,
    val isGameStarted: Boolean = false
)
