package com.hanan.thmanyah.takehome.data.home.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanan.thmanyah.takehome.data.home.local.dao.HomeCacheDao
import com.hanan.thmanyah.takehome.data.home.local.entity.HomeCacheEntity

@Database(
    entities = [
        HomeCacheEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ThmanyahDatabase : RoomDatabase() {
    abstract fun homeCacheDao(): HomeCacheDao
}