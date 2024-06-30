package com.newsreader.presentation.newsfeed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.newsreader.MainActivityViewModel
import com.newsreader.domain.models.News

@Composable
fun NewsFeedContainer(
    activityViewModel: MainActivityViewModel,
    navigateToNewsDetail: (News) -> Unit
) {
    NewsFeed(
        uiStateFlow = activityViewModel.newsUiState.collectAsState(),
        categories = activityViewModel.categories,
        onEvent = activityViewModel::newFeedScreenEvent,
        navigateToNews = navigateToNewsDetail
    )
}