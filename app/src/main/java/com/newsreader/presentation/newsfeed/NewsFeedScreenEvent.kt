package com.newsreader.presentation.newsfeed

import com.newsreader.commonUI.CatListItem
import com.newsreader.domain.models.News

sealed class NewsFeedScreenEvent {
    data class SelectedNews(val newsToShow: News) : NewsFeedScreenEvent()
    data object UpdateBookmarked : NewsFeedScreenEvent()
    data class OnCategorySelected(val selectedCat: CatListItem) : NewsFeedScreenEvent()
}