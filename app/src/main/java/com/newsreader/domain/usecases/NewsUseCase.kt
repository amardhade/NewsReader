package com.newsreader.domain.usecases

import com.newsreader.data.dto.NewsDto
import com.newsreader.data.mappers.toNews
import com.newsreader.data.network.Result
import com.newsreader.domain.NewsRepo
import com.newsreader.domain.models.News
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    suspend fun getNews(selectedCategory: String?): Result {
        val result = newsRepo.fetchNews(selectedCategory)
        when (result) {
            is Result.Success<*> -> {
                val newsDto = result.data as NewsDto
                val news = newsDto.articles ?: mutableListOf()
                return Result.Success(mapRequestedItems(news))
            }

            else -> result
        }
        return result
    }

    fun mapRequestedItems(news: List<NewsDto>): List<News> {
        val mappedItems: MutableList<News> = mutableListOf()
        news.forEachIndexed { index, newsDto -> mappedItems.add(newsDto.toNews(index + 1)) }
        return mappedItems.toList()
    }

}