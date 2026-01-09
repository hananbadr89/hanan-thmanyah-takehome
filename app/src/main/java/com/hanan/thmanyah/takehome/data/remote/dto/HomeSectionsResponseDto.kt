package com.hanan.thmanyah.takehome.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeSectionsResponseDto(
    val sections: List<HomeSectionDto>? = null,
)

@JsonClass(generateAdapter = true)
data class HomeSectionDto(
    val name: String? = null,
    val type: String? = null,
    @property:Json(name = "content_type")
    val contentType: String? = null,
    val order: Int? = null,
    val content: List<HomeContentDto>? = null
)

@JsonClass(generateAdapter = true)
data class HomeContentDto(
    @property:Json(name = "podcast_id") val podcastId: String? = null,
    val name: String? = null,
    val description: String? = null,
    @property:Json(name = "avatar_url") val avatarUrl: String? = null,
    @property:Json(name = "episode_count") val episodeCount: Int? = null,
    val duration: Int? = null,
    val language: String? = null,
    val priority: Int? = null,
    val popularityScore: Int? = null,
    val score: Double? = null
)