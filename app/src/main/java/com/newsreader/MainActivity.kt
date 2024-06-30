package com.newsreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.newsreader.commonUI.BottomAppBar
import com.newsreader.commonUI.NavigationManager
import com.newsreader.ui.theme.NewsReaderTheme
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

    Scaffold(
//        topBar = { TopAppBar() },
        bottomBar = { BottomAppBar() },
        content = { padding ->
            NavigationManager(
                modifier = Modifier.padding(padding),
                activityViewModel = activityViewModel
            )
        }
    )
}