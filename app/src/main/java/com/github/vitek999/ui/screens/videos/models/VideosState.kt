package com.github.vitek999.ui.screens.videos.models

import com.github.vitek999.data.videos.network.VideoDto

data class VideosState(
    val items: List<VideoDto> = emptyList(),
    val isLoading: Boolean = true,
)