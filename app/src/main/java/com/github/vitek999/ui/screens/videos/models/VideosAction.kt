package com.github.vitek999.ui.screens.videos.models

sealed class VideosAction {
    data class OpenVideoPage(val videoId: Long): VideosAction()
}