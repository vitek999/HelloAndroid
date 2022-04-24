package com.github.vitek999.data.videos.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VideosService {

    @GET("videos")
    suspend fun findAll(): Response<List<VideoDto>>

    @GET("videos/{videoId}")
    suspend fun findById(@Path("videoId") videoId: Long): Response<VideoDto?>
}