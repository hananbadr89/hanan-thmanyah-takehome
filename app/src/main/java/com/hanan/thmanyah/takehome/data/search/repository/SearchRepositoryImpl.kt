package com.hanan.thmanyah.takehome.data.search.repository

import com.hanan.thmanyah.takehome.data.common.remote.mapper.decoder.ContentDecoder
import com.hanan.thmanyah.takehome.data.common.remote.mapper.dto.toDomainPage
import com.hanan.thmanyah.takehome.data.search.remote.SearchRemoteDataSource
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionsPage
import com.hanan.thmanyah.takehome.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remote: SearchRemoteDataSource,
    private val decoder: ContentDecoder
) : SearchRepository {

    override suspend fun search(query: String): SectionsPage {
        return remote.search().toDomainPage(decoder)
    }
}