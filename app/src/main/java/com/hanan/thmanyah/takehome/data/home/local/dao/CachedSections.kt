package com.hanan.thmanyah.takehome.data.home.local.dao

import com.hanan.thmanyah.takehome.data.home.remote.dto.SectionsResponseDto

data class CachedSections(
    val dto: SectionsResponseDto,
    val updatedAt: Long
)