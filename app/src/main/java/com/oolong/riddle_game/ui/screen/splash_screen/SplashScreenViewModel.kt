package com.oolong.riddle_game.ui.screen.splash_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.riddle_game.domain.usecase.GetAllQuizDataUseCase
import com.oolong.riddle_game.util.Resource
import com.oolong.riddle_game.util.testWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getAllQuizDataUseCase: GetAllQuizDataUseCase
): ViewModel() {

    private val _splashScreenState = mutableStateOf(SplashScreenState(emptyList()))
    val splashScreenState: State<SplashScreenState> = _splashScreenState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("SplashScreen", "Asd")
            getAllQuizDataUseCase(testWords).collect{ result ->
                when(result) {
                    is Resource.Error -> {
                        result.errorMessage?.let { Log.d("SplashScreen", it) }
                    }
                    is Resource.Loading -> TODO()
                    is Resource.Success -> {
                        Log.d("SplashScreen", result.data.toString())
                        _splashScreenState.value = splashScreenState.value.copy(
                            quizData = result.data
                        )
                    }
                }

//                Log.d("SplashScreen", result.data)
//                _splashScreenState.value = splashScreenState.value.copy(
//                    quizData = it.data
//                )
//                for(entity in it.data) {
//                        Log.d(
//                        "SplashScreen",
//                        "Word: ${entity?.questionWord} \n Question: ${entity.answerMeaning}"
//                    )
//                }
            }

//            _splashScreenState.value = splashScreenState.value.copy(
//                infoText = "Started to pull from remote."
//            )
//            getAllQuizDataUseCase(testWords).collect{
//                for (singleQuizData in it.allQuizData) {
//                    Log.d(
//                        "SplashScreen",
//                        "Word: ${singleQuizData.questionWord} \n Question: ${singleQuizData.answerMeaning}"
//                    )
//                }
//            }
//            _splashScreenState.value = splashScreenState.value.copy(
//                infoText = "All words pulled from remote."
//            )
        }
    }
}