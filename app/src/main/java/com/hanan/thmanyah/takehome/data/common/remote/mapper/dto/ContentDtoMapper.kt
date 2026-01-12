package com.hanan.thmanyah.takehome.data.common.remote.mapper.dto

import com.hanan.thmanyah.takehome.data.common.remote.dto.AudioArticleContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.AudioBookContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.ContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.EpisodeContentDto
import com.hanan.thmanyah.takehome.data.common.remote.dto.PodcastContentDto
import com.hanan.thmanyah.takehome.domain.common.model.model.item.AudioArticleItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.AudioBookItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.EpisodeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.PodcastItem

fun ContentDto.toDomainItem(): HomeItem? {
    return when (this) {
        is PodcastContentDto -> toDomain()
        is EpisodeContentDto -> toDomain()
        is AudioBookContentDto -> toDomain()
        is AudioArticleContentDto -> toDomain()
    }
}

fun PodcastContentDto.toDomain(): PodcastItem? {
    val id = podcastId?.takeIf { it.isNotBlank() } ?: return null
    val title = name?.takeIf { it.isNotBlank() } ?: return null

    return PodcastItem(
        id = id,
        title = title,
        description = description,
        imageUrl = avatarUrl,
        episodeCount = episodeCount,
        durationSec = duration
    )
}

fun EpisodeContentDto.toDomain(): EpisodeItem? {
    val id = episodeId?.takeIf { it.isNotBlank() } ?: return null
    val title = name?.takeIf { it.isNotBlank() } ?: return null

    return EpisodeItem(
        id = id,
        title = title,
        description = description,
        imageUrl = avatarUrl,
        durationSec = duration,
        releaseDate = releaseDate,
        podcastName = podcastName,
        authorName = authorName
    )
}

fun AudioArticleContentDto.toDomain(): AudioArticleItem? {
    val id = articleId?.takeIf { it.isNotBlank() } ?: return null
    val title = name?.takeIf { it.isNotBlank() } ?: return null

    return AudioArticleItem(
        id = id,
        title = title,
        description = description,
        imageUrl = avatarUrl,
        durationSec = duration,
        releaseDate = releaseDate,
        authorName = authorName
    )
}

fun AudioBookContentDto.toDomain(): AudioBookItem? {
    val id = audioBookId?.takeIf { it.isNotBlank() } ?: return null
    val title = name?.takeIf { it.isNotBlank() } ?: return null

    return AudioBookItem(
        id = id,
        title = title,
        imageUrl = avatarUrl,
        durationSec = duration,
        language = language,
        releaseDate = releaseDate,
        description = description,
        authorName = authorName
    )
}