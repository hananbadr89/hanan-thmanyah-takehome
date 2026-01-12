package com.hanan.thmanyah.takehome.data.home.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_cache")
data class HomeCacheEntity(
    @PrimaryKey
    val id: String = CACHE_ID,
    val json: String,
    val currentPage: Int,
    val nextPage: String?,
    val totalPages: Int,
    val updatedAt: Long
) {
    companion object {
        const val CACHE_ID = "home"
    }
}