package com.newsreader.domain

import com.newsreader.domain.models.News

interface NewsRepo {
    suspend fun fetchNews(
        startIndex: Int,
        endIndex: Int,
        newsType: String,
        qParams: String
    ): List<News>
}