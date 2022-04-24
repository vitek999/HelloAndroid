package com.github.vitek999.data.videos.network

import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    val id: Long,
    val videoUrl: String,
    val title: String,
    val speaker: String,
)
