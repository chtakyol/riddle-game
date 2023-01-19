package com.oolong.riddle_game.ui.screen.game_screen

import android.os.CountDownTimer
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

    private val passedWordList: MutableList<Int> = mutableListOf()

    private var countDownTimer: CountDownTimer? = null

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
            GameScreenEvent.PlayClicked -> {
                _uiState.value = uiState.value.copy(
                    isGameStarted = true
                )
                startTimer()
            }
        }
    }

    private fun onPass() {
        updateLetterIndicator(_uiState.value.index, LetterIndicatorState.PASSED)
        val currentIndex = _uiState.value.index
        val currentState = getLetterIndicator(currentIndex)
        if (currentIndex < 25) {
            passedWordList.add(currentIndex)
            _uiState.value = uiState.value.copy(
                index = currentIndex + 1,
                answer = ""
            )
            if (currentState == LetterIndicatorState.EMPTY) {
                updateLetterIndicator(currentIndex + 1, LetterIndicatorState.ACTIVE)
            }
        } else {
            _uiState.value = uiState.value.copy(
                index = passedWordList[currentIndex%25],
                answer = ""
            )
        }
    }

    private fun onRemove() {
        _uiState.value = uiState.value.copy(
            answer = uiState.value.answer.dropLast(1)
        )
    }

    private fun goNextWord() {
        val currentIndex = _uiState.value.index
        val currentState = getLetterIndicator(currentIndex)
        if (currentIndex < 25) {
            _uiState.value = uiState.value.copy(
                index = currentIndex + 1,
                answer = ""
            )
            if (currentState == LetterIndicatorState.EMPTY) {
                updateLetterIndicator(currentIndex + 1, LetterIndicatorState.ACTIVE)
            } else {
                passedWordList.remove(currentIndex)
                Log.d("GameScreen", "Index $passedWordList")
            }
        }
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

    private fun getLetterIndicator(index: Int): LetterIndicatorState? {
        val currentIndicatorStates = uiState.value.letterIndicatorState
        val letterList = currentIndicatorStates.keys.toList()
        return currentIndicatorStates[letterList[index]]
    }

    private fun updateAnswer(letter: String) {
        _uiState.value = uiState.value.copy(
            answer = _uiState.value.answer + letter
        )
    }

    private fun startTimer() {
        val isGameStarted = uiState.value.isGameStarted
        if (isGameStarted) {
            countDownTimer = object : CountDownTimer(60000L, 1000) {

                override fun onTick(millisRemaining: Long) {
                }

                override fun onFinish() {
                    _uiState.value = uiState.value.copy(
                        isGameStarted = false
                    )
                    countDownTimer?.cancel()
                }
            }.start()
        }
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