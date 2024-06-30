package com.newsreader.domain.models

data class News(
    val id: Int,
    val source: String? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val content: String? = null,
    val pubAt: String? = null,
    var isBookmarked: Boolean = false
)
