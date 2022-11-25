package com.oolong.riddle_game.ui.screen.splash_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.domain.IAppUtilityDataRepository
import com.oolong.riddle_game.domain.usecase.GetSingleQuizDataUseCase
import com.oolong.riddle_game.domain.usecase.SaveQuizDataUseCase
import com.oolong.riddle_game.util.Resource
import com.oolong.riddle_game.util.changeMissingWord
import com.oolong.riddle_game.util.prepareTestWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getSingleQuizDataUseCase: GetSingleQuizDataUseCase,
    private val saveQuizDataUseCase: SaveQuizDataUseCase,
    private val getUtilityData: IAppUtilityDataRepository
): ViewModel() {

    private val _splashScreenState = mutableStateOf(SplashScreenState(emptyList()))
    val splashScreenState: State<SplashScreenState> = _splashScreenState

    private val _splashScreenNavigationEvent = MutableSharedFlow<SplashScreenNavigationEvent>()
    val splashScreenNavigationEvent: SharedFlow<SplashScreenNavigationEvent> = _splashScreenNavigationEvent

    private var testWords: MutableList<String> = mutableListOf()
    private var missingLetterIndexes: MutableList<Int> = mutableListOf()
    private var quizDataEntities: MutableList<QuizDataEntity> = mutableListOf()

    private var shouldLoadNewWord = false

    init {
        getCurrentDay()
        testWords = prepareTestWords()
        viewModelScope.launch(Dispatchers.IO) {
            getUtilityData.getAppUtilityData(
                onSuccess = {
                    Log.d("SplashScreen", "Same Day")
                    if (getCurrentDay() != it.lastDateRemoteGet) {
                        shouldLoadNewWord = true
                    }
                },
                onError = { }
            )

            if (shouldLoadNewWord) {
                prepareQuizDataEntities(testWords)
                while (missingLetterIndexes.isNotEmpty()) {
                    changeMissingWords(missingLetterIndexes)
                    prepareQuizDataEntities(testWords)
                }
                saveQuizDataUseCase(quizDataEntities)
                getUtilityData.updateAppUtilityData(
                    onSuccess = { },
                    onError = { },
                    lastDateRemoteGet = getCurrentDay()
                )
            }
            delay(100)
            _splashScreenNavigationEvent.emit(
                SplashScreenNavigationEvent.NavigateToGameScreen
            )
        }
    }

    private suspend fun prepareQuizDataEntities(testWords: List<String>) {
        missingLetterIndexes.clear()
        for ((index, word) in testWords.withIndex()) {
            getSingleQuizDataUseCase(word).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d("SplashScreen", "${result.errorMessage}")
                        missingLetterIndexes.add(index)
                    }
                    is Resource.Loading -> {
                        Log.d("SplashScreen", "Waiting result for '$word' with index $index.")
                    }
                    is Resource.Success -> {
                        Log.d("SplashScreen", "${result.data}")
                        quizDataEntities.add(
                            QuizDataEntity(
                                questionWord = result.data?.questionWord ?: "",
                                answerMeaning = result.data?.answerMeaning ?: ""
                            )
                        )
                    }
                }
            }
        }

        Log.d("SplashScreen", "$missingLetterIndexes couldn't found on remote.")
        Log.d("SplashScreen", "These entities going to save to local $quizDataEntities")
    }

    private fun changeMissingWords(indexes: List<Int>) {
        Log.d("SplashScreen", "Test Words: $testWords")
        for(missingIndex in indexes) {
            testWords = changeMissingWord(missingIndex)
        }
        Log.d("SplashScreen", "Test Words: $testWords")
    }

    private fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        return sdf.format(Date())
    }
}