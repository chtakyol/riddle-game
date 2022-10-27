package com.oolong.riddle_game.ui.screen.splash_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.splashScreenState
    Text(
        text = uiState.value.infoText
    )
}

@Composable
@Preview
fun PreviewSplashScreen() {

}
