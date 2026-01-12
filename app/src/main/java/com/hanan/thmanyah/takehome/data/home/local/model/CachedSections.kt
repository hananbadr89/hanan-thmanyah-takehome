package com.hanan.thmanyah.takehome.data.home.local.model

import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionsResponseDto

data class CachedSections(
    val dto: SectionsResponseDto,
    val paging: PagingState
)

data class PagingState(
    val currentPage: Int,
    val nextPage: String?,
    val totalPages: Int,
    val updatedAt: Long
)