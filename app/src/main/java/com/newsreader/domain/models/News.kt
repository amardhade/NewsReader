package com.newsreader.domain.models

import com.newsreader.data.dto.Source

data class News(
    val source: Source? = Source(),
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
)
