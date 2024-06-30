package com.newsreader.domain

import com.newsreader.data.network.Result

interface NewsRepo {
    suspend fun fetchNews(selectedCat: String?): Result
}