package com.oolong.riddle_game.ui.screen.game_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.riddle_game.domain.usecase.GetQuizDataFromLocalUseCase
import com.oolong.riddle_game.ui.component.letter_indicator.LetterIndicatorState
import com.oolong.riddle_game.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val getQuizDataFromLocalUseCase: GetQuizDataFromLocalUseCase
): ViewModel() {

    private val _uiState = mutableStateOf(GameScreenState())
    val uiState: State<GameScreenState> = _uiState

    init {
        getQuizData()
    }

    fun onEvent(event: GameScreenEvent) {
        when(event) {
            is GameScreenEvent.KeyboardInput -> {
                updateAnswer(event.value)
            }
            GameScreenEvent.EnterClicked -> {
                val result = checkAnswer()
                if (result) {
                    // TODO increase/update score
                    updateLetterIndicator(_uiState.value.index, LetterIndicatorState.CORRECT)
                } else {
                    updateLetterIndicator(_uiState.value.index, LetterIndicatorState.WRONG)
                }
                // TODO add control if there are next word
                goNextWord()
            }
            GameScreenEvent.RemoveClicked -> {
                onRemove()
            }

            GameScreenEvent.PassClicked -> {
                onPass()
            }
        }
    }

    private fun onPass() {
        updateLetterIndicator(_uiState.value.index, LetterIndicatorState.PASSED)
        val currentIndex = _uiState.value.index
        _uiState.value = uiState.value.copy(
            index = currentIndex + 1,
            answer = ""
        )
        updateLetterIndicator(currentIndex + 1, LetterIndicatorState.ACTIVE)
    }

    private fun onRemove() {
        _uiState.value = uiState.value.copy(
            answer = uiState.value.answer.dropLast(1)
        )
    }

    private fun goNextWord() {
        val currentIndex = _uiState.value.index
            _uiState.value = uiState.value.copy(
            index = currentIndex + 1,
            answer = ""
        )
        updateLetterIndicator(currentIndex + 1, LetterIndicatorState.ACTIVE)
    }

    private fun checkAnswer(): Boolean {
        val currentIndex = _uiState.value.index
        val currentUserAnswer = _uiState.value.answer
        val currentCorrectAnswer = _uiState.value.quizData?.get(currentIndex)?.questionWord
        return currentUserAnswer == currentCorrectAnswer
    }

    private fun updateLetterIndicator(index: Int, letterIndicatorState: LetterIndicatorState) {
        val currentIndicatorStates = uiState.value.letterIndicatorState
        val letterList = currentIndicatorStates.keys.toList()
        when(letterIndicatorState) {
            LetterIndicatorState.ACTIVE -> { currentIndicatorStates[letterList[index]] = LetterIndicatorState.ACTIVE }
            LetterIndicatorState.PASSIVE -> { currentIndicatorStates[letterList[index]] = LetterIndicatorState.PASSIVE }
            LetterIndicatorState.CORRECT -> { currentIndicatorStates[letterList[index]] = LetterIndicatorState.CORRECT }
            LetterIndicatorState.WRONG -> { currentIndicatorStates[letterList[index]] = LetterIndicatorState.WRONG }
            LetterIndicatorState.PASSED -> { currentIndicatorStates[letterList[index]] = LetterIndicatorState.PASSED }
            LetterIndicatorState.EMPTY -> { currentIndicatorStates[letterList[index]] = LetterIndicatorState.EMPTY }
        }
        _uiState.value = uiState.value.copy(
            letterIndicatorState = currentIndicatorStates
        )
    }

    private fun updateAnswer(letter: String) {
        _uiState.value = uiState.value.copy(
            answer = _uiState.value.answer + letter
        )
    }

    private fun getQuizData() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuizDataFromLocalUseCase().collect { result ->
                when(result) {
                    is Resource.Error -> {
                        Log.d("GameScreen", "Error: ${result.errorMessage}")
                    }
                    is Resource.Loading -> {
                        Log.d("GameScreen", "Error: ${result.errorMessage}")
                    }
                    is Resource.Success -> {
                            withContext(Dispatchers.Main) {
                                _uiState.value = uiState.value.copy(
                                    index = 0,
                                    quizData = result.data,
                                    remainingTime = 0L
                                )
                        }
                    }
                }
            }
        }
    }
}