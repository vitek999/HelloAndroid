package com.github.vitek999.data.videos.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "video_url") val videoUrl: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "speaker") val speaker: String,
)
