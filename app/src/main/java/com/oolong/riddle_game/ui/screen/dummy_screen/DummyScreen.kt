package com.oolong.riddle_game.ui.screen.dummy_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DummyScreen(
    viewModel: DummyScreenViewModel = hiltViewModel()
) {
    val state = viewModel.uiState
    
    Column {
        Text(text = state.value)
        Button(onClick = { /*TODO*/ }) {
        Text(text = state.value)
        }
    }
    
}