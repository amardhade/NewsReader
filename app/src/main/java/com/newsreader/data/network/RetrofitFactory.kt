package com.newsreader.data.network

import com.google.gson.Gson
import com.newsreader.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory(private val httpClientFactory: HttpClientFactory) {

    fun getRetrofit(): Retrofit {
        return synchronized(NetworkFactory::class.java) {
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(httpClientFactory.getHttpClient()).build()
        }
    }
}