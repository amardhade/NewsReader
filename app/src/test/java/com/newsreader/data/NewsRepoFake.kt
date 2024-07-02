package com.newsreader.data

import com.newsreader.data.network.Result
import com.newsreader.domain.NewsRepo

class NewsRepoFake : NewsRepo {
    override suspend fun fetchNews(selectedCat: String?): Result {
        TODO("Not yet implemented")
    }
}