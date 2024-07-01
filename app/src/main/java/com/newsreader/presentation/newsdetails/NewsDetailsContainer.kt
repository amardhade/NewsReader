package com.newsreader.presentation.newsdetails

import androidx.compose.runtime.Composable
import com.newsreader.MainActivityViewModel

@Composable
fun NewsDetailsContainer(activityViewModel: MainActivityViewModel, selectedNewsId: Int) {
//    activityViewModel.selectedNews?.let {
//        NewsDetails(
//            it,
//            onEvent = activityViewModel::newFeedScreenEvent
//        )
//    }
    activityViewModel.getSelectedNews(selectedNewsId)?.let { selectedNews ->
        NewsDetails(
            selectedNews = selectedNews,
            onEvent = activityViewModel::newFeedScreenEvent
        )
    }

}