package com.newsreader.commonUI

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.newsreader.utlitites.Routes

@Composable
fun BottomTapBar(
    modifier: Modifier,
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    currentRouteState: MutableState<String>,
) {
    var tabIndex by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.NewsFeed.route) }
    val tabs = listOf(
        NavigationItem.NewsFeed,
        NavigationItem.BookmarksNews
    )

    Log.d("News", "TabIndex: ${currentRouteState.value}")
    when (currentRouteState.value) {
        Routes.NEWS_SCREEN -> {
            tabIndex = 0
            currentRoute = Routes.NEWS_SCREEN
        }

        Routes.BOOKMARKS_NEWS_SCREEN -> {
            tabIndex = 1
            currentRoute = Routes.BOOKMARKS_NEWS_SCREEN
        }
    }

    tabs.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            tabIndex = index
        }
    }

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        TabRow(modifier = modifier, selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, item ->
                Tab(text = { Text(item.title) },
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                        currentRoute = item.route
                        navController.navigate(currentRoute) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        }

    }
}

sealed class NavigationItem(var route: String, var title: String) {
    object NewsFeed : NavigationItem(Routes.NEWS_SCREEN, "News Feed")
    object BookmarksNews : NavigationItem(Routes.BOOKMARKS_NEWS_SCREEN, "Bookmarks")
}