package com.hanan.thmanyah.takehome.presentation.home.model

import com.hanan.thmanyah.takehome.domain.model.ContentType
import com.hanan.thmanyah.takehome.domain.model.SectionLayout

data class HomeSectionUi(
    val title: String,
    val layout: SectionLayout,
    val contentType: ContentType,
    val items: List<HomeItemUi>
)

sealed interface HomeItemUi

data class PodcastUi(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val episodes: String?,
    val duration: String?
) : HomeItemUi

data class EpisodeUi(
    val id: String,
    val title: String,
    val podcastName: String?,
    val imageUrl: String?,
    val duration: String?,
    val release: String?
) : HomeItemUi

data class AudioBookUi(
    val id: String,
    val title: String,
    val authorName: String?,
    val imageUrl: String?,
    val duration: String?,
    val release: String?
) : HomeItemUi

data class AudioArticleUi(
    val id: String,
    val title: String,
    val authorName: String?,
    val description: String?,
    val imageUrl: String?,
    val duration: String?,
    val year: String?
) : HomeItemUi