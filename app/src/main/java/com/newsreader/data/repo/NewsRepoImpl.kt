package com.newsreader.data.repo

import com.newsreader.R
import com.newsreader.data.dto.NewsDto
import com.newsreader.data.network.ApiService
import com.newsreader.data.network.Result
import com.newsreader.data.network.UIText
import com.newsreader.domain.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val apiService: ApiService) : NewsRepo,
    ApiWrapper() {

    override suspend fun fetchNews(
        startIndex: Int, endIndex: Int,
        newsType: String, qParams: String, forceRefresh: Boolean
    ): Result {

        val result: Result = apiWrapper { apiService.fetchNews(newsType, qParams) }
        when (result) {
            is Result.Success<*> -> {
                val newsDto = result.data as NewsDto
                return if (newsDto.articles.isNullOrEmpty()) Result.Failure(
                    msg = UIText.StringResource(
                        R.string.no_news_found
                    )
                )
                else Result.Success(newsDto)
            }

            else -> result
        }
        return result
    }


}