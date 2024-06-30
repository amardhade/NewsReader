package com.newsreader.utlitites

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimensions(
    val dp1: Dp = 1.dp,
    val dp2: Dp = 2.dp,
    val dp4: Dp = 4.dp,
    val dp8: Dp = 8.dp,
    val dp10: Dp = 10.dp,
    val dp16: Dp = 16.dp,
    val dp32: Dp = 32.dp,
    val smallSp12: TextUnit = 12.sp,
    val defaultSp14: TextUnit = 14.sp,
    val largeSp16: TextUnit = 16.sp,
    val xlargeSp18: TextUnit = 20.sp,
    val xxlargeSp20: TextUnit = 24.sp
)

val LocalDimensions = compositionLocalOf { Dimensions() }
