package com.oolong.riddle_game.ui.screen.dummy_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.riddle_game.domain.usecase.GetSingleQuizDataUseCase
import com.oolong.riddle_game.domain.usecase.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummyScreenViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase,
    private val getQuizData: GetSingleQuizDataUseCase
): ViewModel() {
    private val _uiState = mutableStateOf("")
    val uiState: State<String> = _uiState

    init {
        viewModelScope.launch {
//            getWordInfoUseCase("dinosaur").collect {
//                Log.d("Dummy", it[0].word.toString())
//                Log.d("Dummy", it.size.toString())
//                _uiState.value = it[0].meanings[0].definitions[0].definition.toString()
//            }
            getQuizData("data").collect {
                Log.d("Dummy", it.questionWord)
                Log.d("Dummy", it.answerMeaning)
            }
        }
    }
}