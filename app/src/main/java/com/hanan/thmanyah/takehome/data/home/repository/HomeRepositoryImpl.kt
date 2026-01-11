package com.hanan.thmanyah.takehome.data.home.repository

import com.hanan.thmanyah.takehome.data.home.mapper.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.home.local.HomeLocalDataSource
import com.hanan.thmanyah.takehome.data.home.mapper.dto.toDomain
import com.hanan.thmanyah.takehome.data.home.remote.HomeRemoteDataSource
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val local: HomeLocalDataSource,
    private val decoder: ContentDecoder,
) : HomeRepository {
    override suspend fun getHomeSections(): SectionsPage {
        local.getCached()?.let { cachedDto ->
            return cachedDto
                .sections
                .orEmpty()
                .mapNotNull { it.toDomain(decoder) }
                .sortedBy { it.order }
                .let { SectionsPage(it) }
        }

        val remoteDto = remote.getHomeSections()

        local.save(remoteDto)

        val sections = remoteDto.sections
            .orEmpty()
            .mapNotNull { it.toDomain(decoder) }
            .sortedBy { it.order }

        return SectionsPage(sections = sections)
    }
}