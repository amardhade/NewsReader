package com.newsreader.data.network

interface NetworkFactory {
    fun <T> getApiService(serviceClass: Class<T>): T
}

class NetworkFactoryImpl(private val retrofitFactory: RetrofitFactory) : NetworkFactory {
    override fun <T> getApiService(serviceClass: Class<T>): T {
        return retrofitFactory.getRetrofit().create(serviceClass)
    }

}