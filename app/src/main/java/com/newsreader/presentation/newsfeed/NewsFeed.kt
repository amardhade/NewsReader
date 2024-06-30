package com.newsreader.presentation.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.newsreader.commonUI.CatListItem
import com.newsreader.commonUI.CategoryList
import com.newsreader.commonUI.NewsItem
import com.newsreader.domain.models.News
import com.newsreader.utlitites.LocalDimensions
import com.newsreader.utlitites.Routes

@Composable
fun NewsFeed(
    listOfNews: List<News>,
    categories: List<CatListItem>,
    onEvent: (NewsFeedScreenEvent) -> Unit = {},
    navigateTo: (String) -> Unit
) {
    val localDimension = LocalDimensions.current

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9.5f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            CategoryList(categories = categories, onItemSelected = { selectedItem ->
                onEvent(NewsFeedScreenEvent.OnCategorySelected(selectedItem))
            })

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(localDimension.dp10)
            ) {
                items(listOfNews, key = { news -> news.id }) { news ->
                    NewsItem(
                        news = news,
                        onItemPress = { newsToNavigate ->
                            onEvent(NewsFeedScreenEvent.SelectedNews(newsToNavigate))
                            navigateTo(Routes.NEWS_DETAIL_SCREEN)
                        })
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewsFeedPreview() {
    NewsFeed(
        listOfNews = mutableListOf(
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