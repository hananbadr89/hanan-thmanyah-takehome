package com.hanan.thmanyah.takehome.domain.model

enum class ContentType {
    PODCAST,
    EPISODE,
    AUDIO_BOOK,
    UNKNOWN
}

enum class SectionLayout {
    SQUARE,
    QUEUE,
    TWO_LINES_GRID,
    BIG_SQUARE,
    UNKNOWN
}

data class HomeSectionsPage(
    val sections: List<HomeSection>
)

data class HomeSection(
    val name: String,
    val type: SectionLayout,
    val contentType: ContentType,
    val order: Int,
    val items: List<HomeItem>
)

sealed interface HomeItem

data class PodcastItem(
    val id: String,
    val name: String,
    val description: String?,
    val avatarUrl: String?,
    val episodeCount: Int?,
    val duration: Int?
) : HomeItem

data class EpisodeItem(
    val id: String,
    val title: String,
    val podcastName: String?,
    val imageUrl: String?,
    val durationSec: Int?,
    val releaseDateIso: String?
) : HomeItem

data class AudioBookItem(
    val id: String,
    val title: String,
    val imageUrl: String?,
    val authorName: String?,
    val durationSec: Int?,
    val releaseDateIso: String?
) : HomeItem