package com.hanan.thmanyah.takehome.data.home.mapper.dto

import com.hanan.thmanyah.takehome.data.home.remote.dto.content.AudioArticleContentDto
import com.hanan.thmanyah.takehome.data.home.remote.dto.content.AudioBookContentDto
import com.hanan.thmanyah.takehome.data.home.remote.dto.content.ContentDto
import com.hanan.thmanyah.takehome.data.home.remote.dto.content.EpisodeContentDto
import com.hanan.thmanyah.takehome.data.home.remote.dto.content.PodcastContentDto
import com.hanan.thmanyah.takehome.domain.home.model.item.AudioArticleItem
import com.hanan.thmanyah.takehome.domain.home.model.item.AudioBookItem
import com.hanan.thmanyah.takehome.domain.home.model.item.EpisodeItem
import com.hanan.thmanyah.takehome.domain.home.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.home.model.item.PodcastItem

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