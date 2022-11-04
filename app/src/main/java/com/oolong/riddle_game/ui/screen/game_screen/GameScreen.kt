package com.oolong.riddle_game.ui.screen.game_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.riddle_game.ui.component.custom_keyboard.KeyboardLayout
import com.oolong.riddle_game.ui.component.letter_indicator.LetterIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    navController: NavController = rememberNavController(),
    viewModel: GameScreenViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val listState: LazyListState = rememberLazyListState()
    val state = viewModel.uiState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LetterIndicator(
            listState = listState,
            letterIndicatorState = state.letterIndicatorState,
            index = state.index
        )
        Text(
            modifier = Modifier.weight(2f),

            textAlign = TextAlign.Center,
            text = state.quizData?.get(state.index)?.answerMeaning?.uppercase() ?: "Question"
        )
        Text(
            textAlign = TextAlign.Center,
            text = state.answer.uppercase()
        )
        Text(
            text = state.quizData?.get(state.index)?.questionWord?.uppercase() ?: "Word"
        )
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyboardLayout(
                onRemoveClicked = {
                    viewModel.onEvent(GameScreenEvent.RemoveClicked)
                },
                onEnterClicked = {
                    viewModel.onEvent(GameScreenEvent.EnterClicked)
                    coroutineScope.launch {
                        delay(100L)
                        listState.animateScrollToItem(viewModel.uiState.value.index)
                    }
                },
                onPassClicked = {
                    viewModel.onEvent(GameScreenEvent.PassClicked)
                    coroutineScope.launch {
                        delay(100L)
                        listState.animateScrollToItem(viewModel.uiState.value.index)
                    }
                }
            ){ letter ->
                viewModel.onEvent(GameScreenEvent.KeyboardInput(letter))
            }
        }
    }
}

@Composable
@Preview
fun PreviewGameScreen() {
    GameScreen()
}