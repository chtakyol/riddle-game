package com.oolong.riddle_game.ui.screen.splash_screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.riddle_game.Screen
import com.oolong.riddle_game.data.local.QuizDataEntity

@Composable
fun SplashScreen(
    navController: NavController = rememberNavController(),
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.splashScreenNavigationEvent.collect { event ->
            when (event) {
                SplashScreenNavigationEvent.NavigateToGameScreen -> {
                    navController.navigate(Screen.GameScreen.route) {
                        popUpTo(Screen.SplashScreen.route) { inclusive = true}
                    }
                }
            }
        }
    }

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
