package com.github.vitek999.ui.screens.videos.models

sealed class VideosEvent {
    object ScreenShown : VideosEvent()
    object ClearAction: VideosEvent()
    data class VideoClicked(val videoId: Long) : VideosEvent()
}