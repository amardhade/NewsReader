package com.newsreader.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.newsreader.utlitites.Dimensions
import com.newsreader.utlitites.LocalDimensions

@Composable
fun ProvidesAppUtils(
    isTablet: Boolean,
    appDimens: Dimensions,
    content: @Composable () -> Unit
) {

    val isTablet = remember { isTablet }

    CompositionLocalProvider(
        InstalledDeviceType provides isTablet,
        LocalDimensions provides appDimens
    ) {
        content()
    }

}

val InstalledDeviceType = compositionLocalOf { false }