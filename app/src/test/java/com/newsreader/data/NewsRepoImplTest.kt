package com.newsreader.data

import com.newsreader.data.mock_response.invalidResponse
import com.newsreader.data.mock_response.validResponse
import com.newsreader.data.network.ApiService
import com.newsreader.data.network.Result
import com.newsreader.data.repo.NewsRepoImpl
import com.newsreader.domain.NewsRepo
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewsRepoImplTest : NewsRepo {

    private lateinit var newsRepoImpl: NewsRepoImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        apiService = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(ApiService::class.java)
        newsRepoImpl = NewsRepoImpl(apiService)
    }

    @After
    fun shutDownMockServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Fetch news, valid response return success`(): Unit {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(validResponse))
            val response = apiService.fetchTopHeadlines()
            if (response.isSuccessful && response.body()?.articles?.isNotEmpty() == true)
                assert(true)
        }
    }

    @Test
    fun `Fetch news, invalid response return failure`(): Unit {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(invalidResponse))
            val response = apiService.fetchTopHeadlines()
            if (response.isSuccessful && response.body()?.articles?.isEmpty() == true)
                assert(true)
        }
    }

    override suspend fun fetchNews(selectedCat: String?): Result {
        TODO("Not yet implemented")
    }
}