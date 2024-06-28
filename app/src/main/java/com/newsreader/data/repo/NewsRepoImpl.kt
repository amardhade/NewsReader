package com.newsreader.data.repo

import com.newsreader.data.network.ApiService
import com.newsreader.domain.NewsRepo
import com.newsreader.domain.models.News
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val apiService: ApiService) : NewsRepo {

    private val list: List<News> = mutableListOf()
    override suspend fun fetchNews(
        startIndex: Int,
        endIndex: Int,
        newsType: String,
        qParams: String
    ): List<News> {

        val response = apiService.fetchNews(newsType, qParams)

        return list
    }
}