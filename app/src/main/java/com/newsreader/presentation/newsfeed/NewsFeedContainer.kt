package com.newsreader.presentation.newsfeed

import androidx.compose.runtime.Composable
import com.newsreader.MainActivityViewModel

@Composable
fun NewsFeedContainer(
    activityViewModel: MainActivityViewModel,
    navigateTo: (String) -> Unit,
    navigateToNewsDetail: (Int) -> Unit,
    onBack: () -> Unit
) {
    NewsFeed(
        listOfNews = activityViewModel.newsState.value,
        categories = activityViewModel.categories,
        onEvent = activityViewModel::newFeedScreenEvent,
        navigateTo = { route -> navigateTo(route) },
        navigateToNewsDetail = { newsId -> navigateToNewsDetail(newsId) },
        onBack = onBack
    )
}