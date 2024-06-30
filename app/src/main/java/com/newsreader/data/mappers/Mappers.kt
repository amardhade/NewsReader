package com.newsreader.data.mappers

import com.newsreader.data.dto.NewsDto
import com.newsreader.domain.models.News

fun NewsDto.toNews(index: Int): News {
    return News(
        id = index,
        source = this.source?.name ?: "",
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: "",
        urlToImage = this.urlToImage ?: "",
        url = this.url ?: "",
        content = this.content ?: "",
        pubAt = this.publishedAt ?: ""
    )
}