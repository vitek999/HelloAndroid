package com.github.vitek999.data.videos

import com.github.vitek999.data.videos.database.VideoDao
import com.github.vitek999.data.videos.database.VideoEntity
import com.github.vitek999.data.videos.network.VideoDto
import com.github.vitek999.data.videos.network.VideosService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideosRepository @Inject constructor(
    private val service: VideosService,
    private val dao: VideoDao,
) {
    suspend fun findAll(): List<VideoDto> = withContext(Dispatchers.IO) {
        val videos = service.findAll().body() ?: emptyList()
        dao.insertAll(*videos.map(VideoDto::asEntity).toTypedArray())
        return@withContext dao.findAll().map(VideoEntity::asDto)
    }

    suspend fun findById(videoId: Long, refresh: Boolean = false): VideoDto? = withContext(Dispatchers.IO) {
        if (refresh) {
            service.findById(videoId).body()?.let { fetched -> dao.insertAll(fetched.asEntity()) }
        }
        return@withContext dao.findById(videoId)?.asDto()
    }
}

private fun VideoDto.asEntity(): VideoEntity = VideoEntity(id, videoUrl, title, speaker)
private fun VideoEntity.asDto(): VideoDto = VideoDto(id, videoUrl, title, speaker)