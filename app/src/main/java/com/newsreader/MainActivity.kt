package com.newsreader

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
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

    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ActivityWrapper(onBack = {
                        doublePressToExit()
                    })
                }
            }
        }
    }

    private fun doublePressToExit() {
        if (doubleBackToExitPressedOnce) {
            this.finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_SHORT
        ).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    @Composable
    fun ActivityWrapper(onBack: () -> Unit) {
        val activityViewModel = hiltViewModel() as MainActivityViewModel
        val navController = rememberNavController()
        val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
        val currentRouteState = rememberSaveable { (mutableStateOf(Routes.NEWS_SCREEN)) }

        // Subscribe to navBackStackEntry, required to get current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute: String = navBackStackEntry?.destination?.route ?: ""
        currentRouteState.value = currentRoute
        when (currentRoute) {
            Routes.SPLASH_SCREEN, Routes.NEWS_DETAIL_SCREEN + "/{newsId}" -> bottomBarState.value =
                false

            Routes.NEWS_SCREEN, Routes.BOOKMARKS_NEWS_SCREEN -> bottomBarState.value = true
        }

        Scaffold(
            bottomBar = {
                BottomTapBar(
                    modifier = Modifier.fillMaxWidth(),
                    navController = navController, bottomBarState = bottomBarState,
                    currentRouteState = currentRouteState
                )
            },
            content = { padding ->
                NavigationManager(
                    modifier = Modifier.padding(padding),
                    activityViewModel = activityViewModel,
                    navHostController = navController,
                    onBack = onBack
                )
            }
        )
    }
}