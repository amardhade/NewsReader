package com.newsreader.data.dto

import com.google.gson.annotations.SerializedName

open class ResponseWrapper<T> {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Int = 0

    @SerializedName("articles")
    var articles: T? = null
}
