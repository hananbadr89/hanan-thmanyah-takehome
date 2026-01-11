package com.hanan.thmanyah.takehome.data.home.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SectionsResponseDto(
    val sections: List<SectionDto>? = null,
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