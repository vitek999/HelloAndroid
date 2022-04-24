package com.github.vitek999.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.vitek999.data.videos.database.VideoDao
import com.github.vitek999.data.videos.database.VideoEntity

@Database(entities = [VideoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun videoDao(): VideoDao
}