package com.newsreader.presentation.splash

sealed class SplashScreenEvent {
    data object FetchNews : SplashScreenEvent()
}