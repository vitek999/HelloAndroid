package com.github.vitek999

import android.content.Context
import androidx.room.Room
import com.github.vitek999.data.AppDatabase
import com.github.vitek999.data.videos.database.VideoDao
import com.github.vitek999.data.videos.VideosRepository
import com.github.vitek999.data.videos.network.VideosService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    @Provides
    @Singleton
    fun provideVideosService(retrofit: Retrofit): VideosService = retrofit.create(VideosService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "Videos").build()

    @Provides
    fun provideVideoDao(appDatabase: AppDatabase): VideoDao = appDatabase.videoDao()

    @Provides
    @Singleton
    fun provideVideosRepository(service: VideosService, dao: VideoDao): VideosRepository = VideosRepository(service, dao)
}