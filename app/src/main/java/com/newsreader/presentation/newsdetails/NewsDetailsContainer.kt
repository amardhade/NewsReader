package com.newsreader.presentation.newsdetails

import androidx.compose.runtime.Composable
import com.newsreader.MainActivityViewModel

@Composable
fun NewsDetailsContainer(activityViewModel: MainActivityViewModel) {
    activityViewModel.selectedNews?.let {
        NewsDetails(
            it,
            onEvent = activityViewModel::newFeedScreenEvent
        )
    }
}