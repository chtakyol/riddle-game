package com.oolong.riddle_game.ui.screen.splash_screen

sealed interface SplashScreenNavigationEvent {
    object NavigateNextScreen: SplashScreenNavigationEvent
}