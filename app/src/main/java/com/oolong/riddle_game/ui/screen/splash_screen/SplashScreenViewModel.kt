package com.oolong.riddle_game.ui.screen.splash_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.riddle_game.domain.usecase.GetAllQuizDataUseCase
import com.oolong.riddle_game.util.testWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getAllQuizDataUseCase: GetAllQuizDataUseCase
): ViewModel() {

    private val _splashScreenState = mutableStateOf(SplashScreenState(infoText = ""))
    val splashScreenState: State<SplashScreenState> = _splashScreenState

    init {
        viewModelScope.launch {
            _splashScreenState.value = splashScreenState.value.copy(
                infoText = "Started to pull from remote."
            )
            getAllQuizDataUseCase(testWords).collect{
                for (singleQuizData in it.allQuizData) {
                    Log.d(
                        "SplashScreen",
                        "Word: ${singleQuizData.questionWord} \n Question: ${singleQuizData.answerMeaning}"
                    )
                }
            }
            _splashScreenState.value = splashScreenState.value.copy(
                infoText = "All words pulled from remote."
            )
        }
    }
}