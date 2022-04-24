package com.github.vitek999.ui.screens.videos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.vitek999.navigation.NavigationTree
import com.github.vitek999.ui.screens.videos.models.VideosAction
import com.github.vitek999.ui.screens.videos.models.VideosEvent
import com.github.vitek999.ui.screens.videos.models.VideosState
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Videos(navController: NavController, videosViewModel: VideosViewModel) {
    val viewState by videosViewModel.viewStates().collectAsState()
    val viewAction by videosViewModel.viewActions().collectAsState(initial = null)

    VideosView(viewState, onClick = { videoId ->
        videosViewModel.obtainEvent(VideosEvent.VideoClicked(videoId))
    })

    LaunchedEffect(key1 = Unit, block = {
        videosViewModel.obtainEvent(VideosEvent.ScreenShown)
    })

    LaunchedEffect(key1 = viewAction, block = {
        when (viewAction) {
            is VideosAction.OpenVideoPage -> {
                val videoId = (viewAction as VideosAction.OpenVideoPage).videoId
                navController.navigate("${NavigationTree.Root.DetailedVideo.name}/$videoId")
            }
            null -> { }
        }
    })
}

@Composable
private fun VideosView(
    viewState: VideosState,
    onClick: (Long) -> Unit
) {
    val state = rememberLazyListState()
    
    Box(
        modifier = Modifier
            .systemBarsPadding()
    ) {
        LazyColumn(state = state) { 
            items(
                items = viewState.items,
                key = { item -> item.id },
            ) { model ->
                Text(text = model.title, modifier = Modifier.clickable {
                    onClick(model.id)
                })
            }
        }
    }
}