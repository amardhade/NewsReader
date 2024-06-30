package com.newsreader.presentation.bookmarknews

import androidx.compose.runtime.Composable
import com.newsreader.MainActivityViewModel

@Composable
fun BookmarkNewsContainer(activityViewModel: MainActivityViewModel, navigateTo: (String) -> Unit) {
    BookmarkNews(
        onEvent = activityViewModel::newFeedScreenEvent,
        bookmarkedNews = activityViewModel.getBookmarkedNews(),
        navigateTo = { route -> navigateTo(route) }
    )
}