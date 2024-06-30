package com.newsreader.presentation.bookmarknews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.newsreader.R
import com.newsreader.commonUI.NewsItem
import com.newsreader.domain.models.News
import com.newsreader.presentation.newsfeed.NewsFeedScreenEvent
import com.newsreader.utlitites.LocalDimensions
import com.newsreader.utlitites.Routes

@Composable
fun BookmarkNews(
    bookmarkedNews: List<News>,
    onEvent: (NewsFeedScreenEvent) -> Unit,
    navigateTo: (String) -> Unit
) {
    val localDimension = LocalDimensions.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(localDimension.dp10),
    ) {
        if (bookmarkedNews.isEmpty()) {
            Text(text = context.getString(R.string.no_items_found))
            return
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(bookmarkedNews, key = { news -> news.id }) { news ->
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