package com.newsreader.commonUI

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
    navHostController: NavHostController = rememberNavController(),
    onBack: () -> Unit
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
                navigateTo = { route ->
                    navHostController.navigate(route)
                }, navigateToNewsDetail = { newsId ->
                    navHostController.navigate(Routes.NEWS_DETAIL_SCREEN + "/${newsId}")
                }, onBack = { onBack() })
        }

        composable(Routes.NEWS_DETAIL_SCREEN + "/{newsId}", arguments = listOf(
            navArgument("newsId") {
                defaultValue = 0
                type = NavType.IntType
            }
        )) { navBackStackEntry ->
            val newsId = navBackStackEntry.arguments?.getInt("newsId")
            NewsDetailsContainer(
                activityViewModel = activityViewModel,
                selectedNewsId = newsId ?: 0,
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(Routes.BOOKMARKS_NEWS_SCREEN) {
            BookmarkNewsContainer(
                activityViewModel,
                navigateToNewDetails = { newsId -> navHostController.navigate(Routes.NEWS_DETAIL_SCREEN + "/${newsId}") },
                onBack = { navHostController.popBackStack() })
        }
    }


}