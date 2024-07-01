package com.newsreader.presentation.bookmarknews

import androidx.compose.runtime.Composable
import com.newsreader.MainActivityViewModel

@Composable
fun BookmarkNewsContainer(
    activityViewModel: MainActivityViewModel,
    navigateToNewDetails: (Int) -> Unit,
    onBack: () -> Unit
) {
    BookmarkNews(
        onEvent = activityViewModel::newFeedScreenEvent,
        bookmarkedNews = activityViewModel.getBookmarkedNews(),
        navigateToNewDetails = { newsId -> navigateToNewDetails(newsId) },
        onBack = onBack
    )
}