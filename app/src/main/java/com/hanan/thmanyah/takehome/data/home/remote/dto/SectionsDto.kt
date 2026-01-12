package com.hanan.thmanyah.takehome.data.home.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SectionsResponseDto(
    val sections: List<SectionDto>? = null,
    val pagination: PaginationDto? = null
)

@JsonClass(generateAdapter = true)
data class SectionDto(
    val name: String? = null,
    val type: String? = null,
    @property:Json(name = "content_type")
    val contentType: String? = null,
    val order: Int? = null,
    val content: List<Map<String, Any?>>? = null
)

@JsonClass(generateAdapter = true)
data class PaginationDto(
    @property:Json(name = "next_page") val nextPage: String? = null,
    @property:Json(name = "total_pages") val totalPages: Int? = null
)