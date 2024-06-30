package com.newsreader.presentation.splash

import androidx.compose.runtime.Composable
import com.newsreader.MainActivityViewModel

@Composable
fun SplashScreenContainer(
    navigate: () -> Unit,
    activityViewModel: MainActivityViewModel
) {
    SplashScreen(
        navigate = navigate,
        onEvent = activityViewModel::splashScreenEvent
    )
}