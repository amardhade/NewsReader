package com.newsreader.domain.usecases

import com.newsreader.data.NewsRepoFake
import com.newsreader.data.dto.NewsDto
import com.newsreader.data.dto.Source
import com.newsreader.domain.NewsRepo
import com.newsreader.domain.models.News
import org.junit.Before
import org.junit.Test


class NewsUseCaseTest {

    private lateinit var newsUseCase: NewsUseCase


    lateinit var newsRepo: NewsRepo

    lateinit var listOfNews: List<NewsDto>

    @Before
    fun setUp() {
        listOfNews = (1..10).map {
            NewsDto(title = "News Tilte", source = Source(id = "", name = "ABP News"))
        }
        newsRepo = NewsRepoFake()
        newsUseCase = NewsUseCase(newsRepo)
    }

    @Test
    fun `Test News ids are getting mapped correctly`() {
        val news: List<News> = newsUseCase.mapRequestedItems(listOfNews)
        val firstNews = news[0]
        if (firstNews.id == 1 && firstNews.source.equals("ABP News")) assert(true) else assert(false)
    }


}