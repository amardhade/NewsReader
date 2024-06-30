package com.newsreader.presentation.newsfeed

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.newsreader.UIState
import com.newsreader.commonUI.CatListItem
import com.newsreader.commonUI.CategoryList
import com.newsreader.commonUI.NewsItem
import com.newsreader.domain.models.News
import com.newsreader.utlitites.LocalDimensions

@Composable
fun NewsFeed(
    uiStateFlow: State<UIState>,
    categories: List<CatListItem>,
    onEvent: (NewsFeedScreenEvent) -> Unit = {},
    navigateToNews: (News) -> Unit
) {
    val localDimension = LocalDimensions.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        CategoryList(categories = categories, onItemSelected = { selectedItem ->
            Log.d("News", "Selcted Cat: ${selectedItem.title}")
            onEvent(NewsFeedScreenEvent.OnCategorySelected(selectedItem))
        })

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(localDimension.dp10)
        ) {
            items(uiStateFlow.value.latestNews, key = { news -> news.id }) { news ->
                NewsItem(
                    news = news,
                    onItemPress = { newsToNavigate ->
                        onEvent(NewsFeedScreenEvent.SelectedNews(newsToNavigate))
                        navigateToNews(newsToNavigate)
                    })
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsFeedPreview() {
    NewsFeed(
        uiStateFlow = mutableStateOf(
            UIState(
                loading = false,
                latestNews = mutableListOf(
                    News(
                        id = 0,
                        title = "News Title News Title ",
                        urlToImage = null
                    ),
                    News(
                        id = 1,
                        title = "News Title News Title News Title News Title",
                        urlToImage = null
                    )
                )
            )
        ), categories = listOf(
            CatListItem(title = "All Category", id = "", isSelected = true),
            CatListItem(title = "Business", id = "business", isSelected = false),
            CatListItem(title = "Entertainment", id = "entertainment", isSelected = false),
            CatListItem(title = "General", id = "general", isSelected = false),
            CatListItem(title = "Health", id = "health", isSelected = false),
            CatListItem(title = "Science", id = "science", isSelected = false),
            CatListItem(title = "Sports", id = "sports", isSelected = false),
            CatListItem(title = "Technology", id = "technology", isSelected = false)
        )
    ) {

    }
}