package com.newsreader.domain.usecases

import com.newsreader.data.dto.NewsDto
import com.newsreader.data.mappers.toNews
import com.newsreader.data.network.Result
import com.newsreader.domain.NewsRepo
import com.newsreader.domain.models.News
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    private var news: List<NewsDto> = mutableListOf()
    suspend fun getNews(
        startIndex: Int, endIndex: Int,
        newsType: String, qParams: String, refreshNews: Boolean
    ): Result {
        if (news.isNotEmpty() && !refreshNews) {
            return Result.Success(mapRequestedItems(startIndex, endIndex))
        }
        val result = newsRepo.fetchNews(startIndex, endIndex, newsType, qParams, refreshNews)
        when (result) {
            is Result.Success<*> -> {
                val newsDto = result.data as NewsDto
                news = newsDto.articles ?: mutableListOf()
                return Result.Success(mapRequestedItems(startIndex, endIndex))
            }

            else -> result
        }
        return result
    }

    private fun mapRequestedItems(startIndex: Int, endIndex: Int): List<News> {
        val mappedItems: MutableList<News> = mutableListOf()
        getRequestedItems(startIndex, endIndex).run {
            this.forEach { newDto -> mappedItems.add(newDto.toNews()) }
        }
        return mappedItems.toList()
    }

    private fun getRequestedItems(startIndex: Int, endIndex: Int): List<NewsDto> {
        val totalNews = news.size
        val limit = if (endIndex > totalNews) totalNews else endIndex
        return news.drop(startIndex).take(limit)
    }

}