package com.hanan.thmanyah.takehome.domain.common.model.model.item

sealed interface HomeItem {
    val id: String
    val title: String
    val imageUrl: String?
}

data class AudioBookItem(
    override val id: String,
    override val title: String,
    override val imageUrl: String?,
    val description: String?,
    val durationSec: Int?,
    val language: String?,
    val releaseDate: String?,
    val authorName: String?
) : HomeItem

data class AudioArticleItem(
    override val id: String,
    override val title: String,
    override val imageUrl: String?,
    val description: String?,
    val durationSec: Int?,
    val releaseDate: String?,
    val authorName: String?,
) : HomeItem

data class EpisodeItem(
    override val id: String,
    override val title: String,
    override val imageUrl: String?,
    val description: String?,
    val durationSec: Int?,
    val releaseDate: String?,
    val podcastName: String?,
    val authorName: String?,
) : HomeItem

data class PodcastItem(
    override val id: String,
    override val title: String,
    override val imageUrl: String?,
    val description: String?,
    val episodeCount: Int?,
    val durationSec: Int?,
) : HomeItem