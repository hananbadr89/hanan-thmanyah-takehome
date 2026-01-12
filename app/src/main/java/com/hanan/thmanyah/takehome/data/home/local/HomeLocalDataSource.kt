package com.hanan.thmanyah.takehome.data.home.local

import com.hanan.thmanyah.takehome.data.home.local.model.CachedSections
import com.hanan.thmanyah.takehome.data.common.remote.dto.SectionsResponseDto
import com.hanan.thmanyah.takehome.data.home.local.dao.HomeCacheDao
import com.hanan.thmanyah.takehome.data.home.local.model.PagingState
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
    private val sectionAdapter = moshi.adapter(SectionsResponseDto::class.java)

    fun observeCached(): Flow<CachedSections?> {
        return dao.observeSections()
            .distinctUntilChanged { old, new ->
                old?.json == new?.json &&
                        old?.updatedAt == new?.updatedAt &&
                        old?.currentPage == new?.currentPage &&
                        old?.nextPage == new?.nextPage &&
                        old?.totalPages == new?.totalPages
            }
            .map { entity ->
                if (entity == null) return@map null

                val dto = sectionAdapter.fromJson(entity.json) ?: return@map null

                val paging = PagingState(
                    currentPage = entity.currentPage,
                    nextPage = entity.nextPage,
                    totalPages = entity.totalPages,
                    updatedAt = entity.updatedAt
                )

                CachedSections(
                    dto = dto,
                    paging = paging
                )
            }
    }

    suspend fun getCached(): CachedSections? {
        val entity = dao.getSections() ?: return null
        val dto = sectionAdapter.fromJson(entity.json) ?: return null

        val paging = PagingState(
            currentPage = entity.currentPage,
            nextPage = entity.nextPage,
            totalPages = entity.totalPages,
            updatedAt = entity.updatedAt
        )

        return CachedSections(
            dto = dto,
            paging = paging
        )
    }

    suspend fun saveAggregated(
        aggregatedDto: SectionsResponseDto,
        paging: PagingState
    ) {
        dao.saveSections(
            HomeCacheEntity(
                json = sectionAdapter.toJson(aggregatedDto),
                updatedAt = paging.updatedAt,
                currentPage = paging.currentPage,
                nextPage = paging.nextPage,
                totalPages = paging.totalPages
            )
        )
    }

    suspend fun save(dto: SectionsResponseDto) {
        val pagination = dto.pagination

        dao.saveSections(
            HomeCacheEntity(
                json = sectionAdapter.toJson(dto),
                currentPage = 1, // first page
                nextPage = pagination?.nextPage,
                totalPages = pagination?.totalPages ?: 1,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun clear() {
        dao.clearSections()
    }
}