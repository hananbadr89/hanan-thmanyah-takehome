package com.hanan.thmanyah.takehome.data.mapper

import com.hanan.thmanyah.takehome.data.remote.dto.HomeContentDto
import com.hanan.thmanyah.takehome.data.remote.dto.HomeSectionDto
import com.hanan.thmanyah.takehome.data.remote.dto.HomeSectionsResponseDto
import com.hanan.thmanyah.takehome.domain.model.AudioBookItem
import com.hanan.thmanyah.takehome.domain.model.ContentType
import com.hanan.thmanyah.takehome.domain.model.EpisodeItem
import com.hanan.thmanyah.takehome.domain.model.HomeItem
import com.hanan.thmanyah.takehome.domain.model.HomeSection
import com.hanan.thmanyah.takehome.domain.model.HomeSectionsPage
import com.hanan.thmanyah.takehome.domain.model.PodcastItem
import com.hanan.thmanyah.takehome.domain.model.SectionLayout

fun HomeSectionsResponseDto.toDomain(): HomeSectionsPage {
    return HomeSectionsPage(
        sections = sections
            .orEmpty()
            .mapNotNull { it.toDomainOrNull() }
            .sortedBy { it.order }
    )
}

private fun HomeSectionDto.toDomainOrNull(): HomeSection? {
    val contentType = contentType.requiredNonBlank()?.toContentType() ?: return null

    val items = content.orEmpty().mapNotNull { it.toDomainItemOrNull(contentType) }
    if (items.isEmpty()) return null

    return HomeSection(
        name = name.requiredNonBlank() ?: return null,
        type = type.requiredNonBlank()?.toSectionLayout() ?: return null,
        contentType = contentType,
        order = order ?: Int.MAX_VALUE,
        items = items
    )
}

private fun HomeContentDto.toDomainItemOrNull(contentType: ContentType): HomeItem? {
    return when (contentType) {
        ContentType.PODCAST -> {
            PodcastItem(
                id = podcastId?.requiredNonBlank() ?: return null,
                name = name.requiredNonBlank() ?: return null,
                description = description,
                avatarUrl = avatarUrl,
                episodeCount = episodeCount,
                duration = duration
            )
        }

        ContentType.EPISODE -> EpisodeItem(
            id = episodeId?.takeIf { it.isNotBlank() } ?: return null,
            title = name?.takeIf { it.isNotBlank() } ?: return null,
            podcastName = podcastName?.takeIf { it.isNotBlank() },
            imageUrl = avatarUrl,
            durationSec = duration,
            releaseDateIso = releaseDate
        )

        ContentType.AUDIO_BOOK -> AudioBookItem(
            id = audioBookId?.takeIf { it.isNotBlank() } ?: return null,
            title = name?.takeIf { it.isNotBlank() } ?: return null,
            imageUrl = avatarUrl,
            authorName = authorName?.takeIf { it.isNotBlank() },
            durationSec = duration,
            releaseDateIso = releaseDate
        )

        ContentType.UNKNOWN -> null
    }
}

private fun String.toContentType(): ContentType =
    trim().lowercase().let {
        when (it) {
            "podcast" -> ContentType.PODCAST
            "episode" -> ContentType.EPISODE
            "audio_book" -> ContentType.AUDIO_BOOK
            else -> ContentType.UNKNOWN
        }
    }

fun String.toSectionLayout(): SectionLayout =
    trim().lowercase().let {
        when (it) {
            "square" -> SectionLayout.SQUARE
            "queue" -> SectionLayout.QUEUE
            "2_lines_grid" -> SectionLayout.TWO_LINES_GRID
            "big_square" -> SectionLayout.BIG_SQUARE
            else -> SectionLayout.UNKNOWN
        }
    }

private fun String?.requiredNonBlank(): String? = this?.takeIf { it.isNotBlank() }
