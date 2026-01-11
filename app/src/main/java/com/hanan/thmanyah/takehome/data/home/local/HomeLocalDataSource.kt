package com.hanan.thmanyah.takehome.data.home.local

import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionsResponseDto
import com.hanan.thmanyah.takehome.data.home.local.dao.HomeCacheDao
import com.hanan.thmanyah.takehome.data.home.local.entity.HomeCacheEntity
import com.squareup.moshi.Moshi
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(
    private val dao: HomeCacheDao,
    private val moshi: Moshi
) {
    private val adapter = moshi.adapter(SectionsResponseDto::class.java)

    suspend fun getCached(): SectionsResponseDto? {
        val entity = dao.get() ?: return null
        return adapter.fromJson(entity.json)
    }

    suspend fun save(dto: SectionsResponseDto) {
        dao.save(
            HomeCacheEntity(
                json = adapter.toJson(dto),
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun clear() = dao.clear()
}