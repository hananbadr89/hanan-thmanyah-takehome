package com.hanan.thmanyah.takehome.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeSectionsResponseDto(
    val sections: List<SectionDto>,
    val pagination: PaginationDto
)

@JsonClass(generateAdapter = true)
data class PaginationDto(
    @field:Json(name = "next_page")
    val nextPage: String?,
    @field:Json(name = "total_pages")
    val totalPages: Int?
)

@JsonClass(generateAdapter = true)
data class SectionDto(
    val name: String?,
    val type: String?,
    @field:Json(name = "content_type")
    val contentType: String?,
    val order: Int?,
    val content: List<Map<String, Any?>>
)