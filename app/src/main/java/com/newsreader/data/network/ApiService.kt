package com.newsreader.data.network

import com.newsreader.BuildConfig
import com.newsreader.data.dto.NewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("country") country: String = "in",
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<NewsDto>

}