package com.github.vitek999.data.videos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos")
    fun findAll(): List<VideoEntity>

    @Query("SELECT * FROM videos WHERE id = :videoId")
    fun findById(videoId: Long): VideoEntity?

    @Insert
    fun insertAll(vararg videos: VideoEntity)

    @Delete
    fun delete(video: VideoEntity)

    @Query("DELETE FROM videos")
    fun deleteAll()
}