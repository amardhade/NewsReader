package com.newsreader.data.network

import com.newsreader.data.dto.NewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/{type}")
    suspend fun fetchNews(
        @Path("type") type: String,
        @Query("queryParams") queryParams: String
    ): Response<NewsDto>
}