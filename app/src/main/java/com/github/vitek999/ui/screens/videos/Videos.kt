package com.github.vitek999.ui.screens.videos

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
import com.github.vitek999.ui.screens.videos.models.VideosEvent
import com.github.vitek999.ui.screens.videos.models.VideosState
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Videos(videosViewModel: VideosViewModel) {
    val viewState by videosViewModel.viewStates().collectAsState()

    VideosView(viewState)

    LaunchedEffect(key1 = Unit, block = {
        videosViewModel.obtainEvent(VideosEvent.ScreenShown)
    })
}

@Composable
private fun VideosView(
    viewState: VideosState
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
                Text(text = model.title)
            }
        }
    }
}