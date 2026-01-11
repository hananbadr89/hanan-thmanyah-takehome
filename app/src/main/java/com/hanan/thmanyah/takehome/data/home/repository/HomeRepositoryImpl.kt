package com.hanan.thmanyah.takehome.data.home.repository

import com.hanan.thmanyah.takehome.data.home.dto.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.home.mapper.toDomain
import com.hanan.thmanyah.takehome.data.home.remote.HomeRemoteDataSource
import com.hanan.thmanyah.takehome.domain.home.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remote: HomeRemoteDataSource,
    private val decoder: ContentDecoder,
) : HomeRepository {
    override suspend fun getHomeSections(): SectionsPage {
        val dto = remote.getHomeSections()

        val sections = dto.sections
            .orEmpty()
            .mapNotNull { it.toDomain(decoder) }
            .sortedBy { it.order }

        return SectionsPage(sections = sections)
    }
}