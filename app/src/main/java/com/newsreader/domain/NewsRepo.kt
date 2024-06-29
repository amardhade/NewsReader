package com.newsreader.domain

import com.newsreader.data.network.Result

interface NewsRepo {
    suspend fun fetchNews(
        startIndex: Int, endIndex: Int,
        newsType: String, qParams: String, forceRefresh: Boolean
    ): Result
}