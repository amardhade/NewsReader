package com.newsreader.di

import android.content.Context
import android.net.ConnectivityManager
import com.ganakalabs.data.network.connectivity.NetworkMonitor
import com.newsreader.data.network.ApiService
import com.newsreader.data.network.HttpClientFactory
import com.newsreader.data.network.NetworkFactory
import com.newsreader.data.network.NetworkFactoryImpl
import com.newsreader.data.network.RetrofitFactory
import com.newsreader.data.repo.NewsRepoImpl
import com.newsreader.domain.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDI {


    @Singleton
    @Provides
    fun providesConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun providesNetworkMonitor(connectivityManager: ConnectivityManager): NetworkMonitor {
        return NetworkMonitor(connectivityManager)
    }

    @Singleton
    @Provides
    fun providesOkHttpClientFactory(): HttpClientFactory {
        return HttpClientFactory()
    }

    @Singleton
    @Provides
    fun providesRetrofitFactory(httpClientFactory: HttpClientFactory): RetrofitFactory {
        return RetrofitFactory(httpClientFactory)
    }

    @Singleton
    @Provides
    fun providesNetworkFactory(retrofitFactory: RetrofitFactory): NetworkFactory {
        return NetworkFactoryImpl(retrofitFactory)
    }

    @Singleton
    @Provides
    fun providesApiService(networkFactory: NetworkFactory): ApiService =
        networkFactory.getApiService(ApiService::class.java)

    @Singleton
    @Provides
    fun providesNewsRepo(apiService: ApiService): NewsRepo = NewsRepoImpl(apiService)
}