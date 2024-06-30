package com.newsreader

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsreader.commonUI.CatListItem
import com.newsreader.data.network.Result
import com.newsreader.domain.models.News
import com.newsreader.domain.usecases.NewsUseCase
import com.newsreader.presentation.newsfeed.NewsFeedScreenEvent
import com.newsreader.presentation.splash.SplashScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val newsUseCase: NewsUseCase) :
    ViewModel() {

//    private val _newsUiState = MutableStateFlow(UIState())
//    val newsUiState = _newsUiState.asStateFlow()

    var newsState: MutableState<List<News>> = mutableStateOf(listOf())

    private var selectedCategory: CatListItem? = null
    var selectedNews: News? = null
    var categories by mutableStateOf(
        listOf(
            CatListItem(title = "All Category", id = "", isSelected = true),
            CatListItem(title = "Business", id = "business", isSelected = false),
            CatListItem(title = "Entertainment", id = "entertainment", isSelected = false),
            CatListItem(title = "General", id = "general", isSelected = false),
            CatListItem(title = "Health", id = "health", isSelected = false),
            CatListItem(title = "Science", id = "science", isSelected = false),
            CatListItem(title = "Sports", id = "sports", isSelected = false),
            CatListItem(title = "Technology", id = "technology", isSelected = false)
        )
    )
        private set

    init {

    }

    fun splashScreenEvent(splashScreenEvent: SplashScreenEvent) {
        when (splashScreenEvent) {
            is SplashScreenEvent.FetchNews -> {
                fetchNews()
            }
        }
    }

    fun newFeedScreenEvent(newsFeedScreenEvent: NewsFeedScreenEvent) {
        when (newsFeedScreenEvent) {
            is NewsFeedScreenEvent.OnCategorySelected -> {
                selectedCategory = newsFeedScreenEvent.selectedCat
                categories = categories.map { category ->
                    if (category == selectedCategory) category.copy(isSelected = true)
                    else category.copy(isSelected = false)
                }
                fetchNews()
            }

            is NewsFeedScreenEvent.SelectedNews -> {
                selectedNews = newsFeedScreenEvent.newsToShow
            }

            is NewsFeedScreenEvent.UpdateBookmarked -> {
                newsState.value = newsState.value.map { news ->
                    if (news == newsFeedScreenEvent.newsToUpdate) news.copy(isBookmarked = !selectedNews?.isBookmarked!!)
                    else news
                }
            }
        }
    }


    private fun fetchNews() {
        viewModelScope.launch {
            val qParams = "q=tesla&from=2024-05-29&sortBy=publishedAt&apiKey=${BuildConfig.API_KEY}"
//            updateLoader(shouldShow = true)
            val category: String? = selectedCategory?.id
            val result: Result = newsUseCase.getNews(category)
//            updateLoader(shouldShow = false)
            when (result) {
                is Result.Success<*> -> {
                    val news = result.data as List<*>
                    Log.d("News", "Success: ${news.size}")
                    updateNews(news as List<News>)
                }

                is Result.Failure -> {
                    Log.d("News", "Failed to getch news: ")
                }

                Result.Loading -> TODO()
            }

        }
    }

    fun getBookmarkedNews(): List<News> {
        return newsState.value.filter { item -> item.isBookmarked }
    }

    private fun updateNews(latestNews: List<News>) {
        newsState.value = latestNews
    }

}

data class UIState(
    val loading: Boolean = false,
    val latestNews: List<News> = mutableListOf()
)