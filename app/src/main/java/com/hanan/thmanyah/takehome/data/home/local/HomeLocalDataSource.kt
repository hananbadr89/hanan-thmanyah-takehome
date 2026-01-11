package com.hanan.thmanyah.takehome.data.home.local

import com.hanan.thmanyah.takehome.data.home.local.dao.CachedSections
import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionsResponseDto
import com.hanan.thmanyah.takehome.data.home.local.dao.HomeCacheDao
import com.hanan.thmanyah.takehome.data.home.local.entity.HomeCacheEntity
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(
    private val dao: HomeCacheDao,
    moshi: Moshi
) {
    private val adapter = moshi.adapter(SectionsResponseDto::class.java)

    fun observeCached(): Flow<CachedSections?> {
        return dao.observe()
            .distinctUntilChanged { old, new -> old?.json == new?.json && old?.updatedAt == new?.updatedAt }
            .map { entity ->
                if (entity == null) return@map null
                val dto = adapter.fromJson(entity.json) ?: return@map null
                CachedSections(dto = dto, updatedAt = entity.updatedAt)
            }
    }

    suspend fun getCached(): CachedSections? {
        val entity = dao.get() ?: return null
        val dto = adapter.fromJson(entity.json) ?: return null
        return CachedSections(
            dto = dto,
            updatedAt = entity.updatedAt
        )
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