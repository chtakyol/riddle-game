package com.oolong.riddle_game.ui.screen.splash_screen

sealed class SplashScreenNavigationEvent {
    object NavigateToGameScreen: SplashScreenNavigationEvent()
}