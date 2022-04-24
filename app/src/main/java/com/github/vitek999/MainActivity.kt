package com.github.vitek999

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.vitek999.navigation.NavigationTree
import com.github.vitek999.ui.screens.video.Video
import com.github.vitek999.ui.screens.videos.Videos
import com.github.vitek999.ui.screens.videos.VideosViewModel
import com.github.vitek999.ui.theme.HelloAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val videosViewModel by viewModels<VideosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAndroidTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationTree.Root.Videos.name
                    ) {
                        composable(NavigationTree.Root.Videos.name) {
                            val viewModel = hiltViewModel<VideosViewModel>()
                            Videos(navController, viewModel)
                        }
                        composable(
                            "${NavigationTree.Root.DetailedVideo.name}/{videoId}",
                            arguments = listOf(navArgument("videoId") { type = NavType.StringType }),
                        ) {
                            val videoId = it.arguments?.getString("videoId")?.toLong()!!
                            Video(videoId)
                        }
                    }
                }
            }
        }
    }
}