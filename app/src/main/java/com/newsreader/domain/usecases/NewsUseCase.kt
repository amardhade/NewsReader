package com.newsreader.domain.usecases

import com.newsreader.data.dto.NewsDto
import com.newsreader.data.mappers.toNews
import com.newsreader.data.network.Result
import com.newsreader.domain.NewsRepo
import com.newsreader.domain.models.News
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    private var news: List<NewsDto> = mutableListOf()
    suspend fun getNews(selectedCategory: String?): Result {
        val result = newsRepo.fetchNews(selectedCategory)
        when (result) {
            is Result.Success<*> -> {
                val newsDto = result.data as NewsDto
                news = newsDto.articles ?: mutableListOf()
                return Result.Success(mapRequestedItems())
            }

            else -> result
        }
        return result
    }

    private fun mapRequestedItems(): List<News> {
        val mappedItems: MutableList<News> = mutableListOf()
        news.forEachIndexed { index, newsDto -> mappedItems.add(newsDto.toNews(index + 1)) }
        return mappedItems.toList()
    }

}