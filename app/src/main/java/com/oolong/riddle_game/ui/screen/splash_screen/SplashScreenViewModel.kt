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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _splashScreenNavigationEvent = MutableSharedFlow<SplashScreenNavigationEvent>()
    val splashScreenNavigationEvent: SharedFlow<SplashScreenNavigationEvent> = _splashScreenNavigationEvent

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllQuizDataUseCase(testWords).collect{ result ->
                when(result) {
                    is Resource.Error -> {
                        result.errorMessage?.let { Log.d("SplashScreen", it) }
                    }
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        _splashScreenState.value = splashScreenState.value.copy(
                            quizData = result.data
                        )
                        _splashScreenNavigationEvent.emit(
                            SplashScreenNavigationEvent.NavigateToGameScreen
                        )
                    }
                }
            }
        }
    }
}