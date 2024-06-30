package com.newsreader.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.newsreader.utlitites.LocalDimensions
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigate: () -> Unit,
    onEvent: (SplashScreenEvent) -> Unit
) {
    val dimension = LocalDimensions.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Splash Screen", fontSize = dimension.xxlargeSp20)
    }

    LaunchedEffect(key1 = true) {
        onEvent(SplashScreenEvent.FetchNews)
        delay(2000)
        navigate()
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen(navigate = { /*TODO*/ }) {

    }
}