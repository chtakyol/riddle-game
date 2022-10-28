package com.oolong.riddle_game.ui.screen.splash_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.oolong.riddle_game.data.local.QuizDataEntity

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.splashScreenState
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        uiState.value.quizData!!.forEach {
            Text(text = it.questionWord)
            Text(text = it.answerMeaning)
        }
    }
}

@Composable
@Preview
fun PreviewSplashScreen() {

}
