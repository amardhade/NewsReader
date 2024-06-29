package com.newsreader.data.mappers

import com.newsreader.data.dto.NewsDto
import com.newsreader.domain.models.News

fun NewsDto.toNews(): News {
    return News(
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: ""
    )
}