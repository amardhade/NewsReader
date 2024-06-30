package com.newsreader.presentation.newsfeed

import com.newsreader.commonUI.CatListItem
import com.newsreader.domain.models.News

sealed class NewsFeedScreenEvent {
    data class SelectedNews(val newsToShow: News) : NewsFeedScreenEvent()
    data class UpdateBookmarked(val newsToUpdate: News) : NewsFeedScreenEvent()
    data class OnCategorySelected(val selectedCat: CatListItem) : NewsFeedScreenEvent()
}