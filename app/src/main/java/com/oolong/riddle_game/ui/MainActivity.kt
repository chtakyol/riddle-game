package com.oolong.riddle_game.ui

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oolong.riddle_game.Screen
import com.oolong.riddle_game.ui.component.letter_indicator.LetterIndicator
import com.oolong.riddle_game.ui.screen.game_screen.GameScreen
import com.oolong.riddle_game.ui.screen.splash_screen.SplashScreen
import com.oolong.riddle_game.ui.theme.RiddlegameTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            RiddlegameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF1F2F2)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(
                            route = Screen.SplashScreen.route
                        ) {
                            SplashScreen(
                                navController = navController
                            )
                        }

                        composable(
                            route = Screen.GameScreen.route
                        ) {
                            GameScreen(
                                navController = navController
                            )
                        }
                    }

//                    SplashScreen()
//                    GameScreen()
                }
            }
        }
    }
}
