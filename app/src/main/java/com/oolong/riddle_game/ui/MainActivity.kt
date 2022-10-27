package com.oolong.riddle_game.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.oolong.riddle_game.ui.component.LetterIndicator
import com.oolong.riddle_game.ui.screen.dummy_screen.DummyScreen
import com.oolong.riddle_game.ui.screen.splash_screen.SplashScreen
import com.oolong.riddle_game.ui.theme.RiddlegameTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            var index = remember { 1 }

            RiddlegameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    TestScreen(
//                        listState = listState,
//                        index = index - 1
//                    ) {
//                        coroutineScope.launch {
//                            listState.animateScrollToItem(index++)
//                        }
//                    }
                    SplashScreen()
                }
            }
        }
    }
}

@Composable
fun TestScreen(
    listState: LazyListState = rememberLazyListState(),
    index: Int = 0,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LetterIndicator(
            listState = listState,
            index = index
        )
        Button(
            onClick = {
                onClick()
            }
        ) {

        }
    }
}

@Composable
@Preview
fun previewMainScreen() {
    TestScreen(){}
}
