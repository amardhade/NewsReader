package com.newsreader.data.network

import com.newsreader.utlitites.UIText

sealed interface Result {
    data class Success<T>(val data: T) : Result
    data object Loading : Result
    data class Failure(val msg: UIText) : Result
}