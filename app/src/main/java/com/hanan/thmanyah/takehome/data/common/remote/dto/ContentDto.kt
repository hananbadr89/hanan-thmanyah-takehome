package com.hanan.thmanyah.takehome.data.common.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed interface ContentDto

@JsonClass(generateAdapter = true)
data class AudioArticleContentDto(
    @property:Json(name = "article_id") val articleId: String? = null,
    val name: String? = null,
    val description: String? = null,
    @property:Json(name = "author_name") val authorName: String? = null,
    @property:Json(name = "avatar_url") val avatarUrl: String? = null,
    @property:Json(name = "release_date") val releaseDate: String? = null,
    val duration: Int? = null,
    val language: String? = null,
    val score: Double? = null
) : ContentDto

@JsonClass(generateAdapter = true)
data class AudioBookContentDto(
    @property:Json(name = "audiobook_id") val audioBookId: String? = null,
    val name: String? = null,
    @property:Json(name = "author_name") val authorName: String? = null,
    @property:Json(name = "avatar_url") val avatarUrl: String? = null,
    @property:Json(name = "release_date") val releaseDate: String? = null,
    val duration: Int? = null,
    val language: String? = null,
    val score: Double? = null,
    val description: String? = null
) : ContentDto

@JsonClass(generateAdapter = true)
data class EpisodeContentDto(
    @property:Json(name = "episode_id") val episodeId: String? = null,
    val name: String? = null,
    val description: String? = null,
    @property:Json(name = "avatar_url") val avatarUrl: String? = null,
    @property:Json(name = "podcast_name") val podcastName: String? = null,
    @property:Json(name = "release_date") val releaseDate: String? = null,
    @property:Json(name = "author_name") val authorName: String? = null,
    val duration: Int? = null,
    val score: Double? = null,
    @property:Json(name = "podcastPopularityScore") val podcastPopularityScore: Int? = null,
) : ContentDto

@JsonClass(generateAdapter = true)
data class PodcastContentDto(
    @property:Json(name = "podcast_id") val podcastId: String? = null,
    val name: String? = null,
    val description: String? = null,
    @property:Json(name = "avatar_url") val avatarUrl: String? = null,
    @property:Json(name = "episode_count") val episodeCount: Any? = null,
    val duration: Any? = null,
    val language: String? = null,
    val priority: Any? = null,
    val popularityScore: Any? = null,
    val score: Any? = null
) : ContentDto