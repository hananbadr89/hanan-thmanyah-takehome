package com.hanan.thmanyah.takehome.data.home.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanan.thmanyah.takehome.data.home.local.entity.HomeCacheEntity

@Dao
interface HomeCacheDao {
    @Query("SELECT * FROM home_cache WHERE id = :id LIMIT 1")
    suspend fun get(id: String = "home"): HomeCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: HomeCacheEntity)

    @Query("DELETE FROM home_cache")
    suspend fun clear()
}