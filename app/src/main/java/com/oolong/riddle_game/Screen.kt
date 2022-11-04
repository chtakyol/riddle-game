package com.oolong.riddle_game

sealed class Screen(val route: String) {
    object SplashScreen: Screen(route = "splash_screen")
    object GameScreen: Screen(route = "game_screen")
}
