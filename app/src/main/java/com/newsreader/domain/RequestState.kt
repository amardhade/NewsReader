package com.newsreader.domain

sealed class RequestState<out T> {

    data class Success<out T>(val data: T) : RequestState<T>()
    data object Loading : RequestState<Nothing>()

    data object Idle : RequestState<Nothing>()
    data class Error(val errorMsg: String) : RequestState<Nothing>()


    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getSuccessData() = (this as Success).data

    fun getErrorMsg() = (this as Error).errorMsg


}