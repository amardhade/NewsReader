package com.newsreader.data.repo

import com.newsreader.R
import com.newsreader.data.network.Result
import com.newsreader.utlitites.UIText
import retrofit2.Response

abstract class ApiWrapper {
    suspend fun <T> apiWrapper(api: suspend () -> Response<T>): Result {
        return try {
            val response = api()
            if (response.isSuccessful && response.body() != null)
                Result.Success(data = response.body())
            else Result.Failure(msg = UIText.DynamicString(response.message()))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(msg = UIText.StringResource(R.string.prblm_connecting_with_server))
        }
    }
}