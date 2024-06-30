package com.newsreader.commonUI

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newsreader.MainActivityViewModel
import com.newsreader.presentation.bookmarknews.BookmarkNewsContainer
import com.newsreader.presentation.newsdetails.NewsDetailsContainer
import com.newsreader.presentation.newsfeed.NewsFeedContainer
import com.newsreader.presentation.splash.SplashScreenContainer
import com.newsreader.utlitites.Routes


@Composable
fun NavigationManager(
    modifier: Modifier = Modifier,
    activityViewModel: MainActivityViewModel,
//    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navHostController: NavHostController = rememberNavController()
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Routes.SPLASH_SCREEN
    ) {

        composable(Routes.SPLASH_SCREEN) {
            SplashScreenContainer(navigate = {
                navHostController.navigate(route = Routes.NEWS_SCREEN) {
                    popUpTo(route = Routes.SPLASH_SCREEN) { inclusive = true }
                }
            }, activityViewModel = activityViewModel)
        }

        composable(Routes.NEWS_SCREEN) {
            NewsFeedContainer(
                activityViewModel = activityViewModel,
                navigateToNewsDetail = { selectedNews ->
                    navHostController.navigate(Routes.NEWS_DETAIL_SCREEN)
                })
        }

        composable(Routes.NEWS_DETAIL_SCREEN) {
            NewsDetailsContainer(activityViewModel = activityViewModel)
        }

        composable(Routes.BOOKMARKS_NEWS_SCREEN) {
            BookmarkNewsContainer()
        }
    }


}