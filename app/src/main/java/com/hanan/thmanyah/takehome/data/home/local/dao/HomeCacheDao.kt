package com.hanan.thmanyah.takehome.data.home.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanan.thmanyah.takehome.data.home.local.entity.HomeCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeCacheDao {
    @Query("SELECT * FROM home_cache WHERE id = :id LIMIT 1")
    suspend fun getSections(id: String = "home"): HomeCacheEntity?

    @Query("SELECT * FROM home_cache WHERE id = :id LIMIT 1")
    fun observeSections(id: String = HomeCacheEntity.CACHE_ID): Flow<HomeCacheEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSections(entity: HomeCacheEntity)

    @Query("DELETE FROM home_cache")
    suspend fun clearSections()
}