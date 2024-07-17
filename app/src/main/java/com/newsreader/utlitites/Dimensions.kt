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

// // Phone width < 600dp
val CompactDeviceDimens = Dimensions()

// Medium Tablet 600dp ≤ width < 840dp
val MediumDeviceDimens = Dimensions(
    dp1 = 1.dp,
    dp2 = 2.dp,
    dp4 = 4.dp,
    dp8 = 8.dp,
    dp10 = 10.dp,
    dp16 = 16.dp,
    dp32 = 32.dp,
    smallSp12 = 14.sp,
    defaultSp14 = 16.sp,
    largeSp16 = 18.sp,
    xlargeSp18 = 22.sp,
    xxlargeSp20 = 26.sp
)

// Large Tablet width ≥ 840dp
val ExpandedDeviceDimens = Dimensions(
    dp1 = 1.dp,
    dp2 = 2.dp,
    dp4 = 4.dp,
    dp8 = 8.dp,
    dp10 = 10.dp,
    dp16 = 16.dp,
    dp32 = 32.dp,
    smallSp12 = 16.sp,
    defaultSp14 = 18.sp,
    largeSp16 = 20.sp,
    xlargeSp18 = 24.sp,
    xxlargeSp20 = 28.sp
)

val LocalDimensions = compositionLocalOf { CompactDeviceDimens }
