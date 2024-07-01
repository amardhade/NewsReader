package com.newsreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.newsreader.commonUI.BottomTapBar
import com.newsreader.commonUI.NavigationManager
import com.newsreader.ui.theme.NewsReaderTheme
import com.newsreader.utlitites.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ActivityWrapper()
                }
            }
        }
    }
}

@Composable
fun ActivityWrapper() {
    val activityViewModel = hiltViewModel() as MainActivityViewModel
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

    // Subscribe to navBackStackEntry, required to get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Routes.SPLASH_SCREEN, Routes.NEWS_DETAIL_SCREEN + "/{newsId}" -> bottomBarState.value =
            false
        Routes.NEWS_SCREEN, Routes.BOOKMARKS_NEWS_SCREEN -> bottomBarState.value = true
    }

    Scaffold(
        bottomBar = {
            BottomTapBar(
                modifier = Modifier.fillMaxWidth(),
                navController = navController, bottomBarState = bottomBarState
            )
        },
        content = { padding ->
            NavigationManager(
                modifier = Modifier.padding(padding),
                activityViewModel = activityViewModel,
                navHostController = navController
            )
        }
    )
}