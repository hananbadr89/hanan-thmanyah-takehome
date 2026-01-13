package com.hanan.thmanyah.takehome.presentation.mapper

import com.hanan.thmanyah.takehome.domain.common.model.model.item.AudioArticleItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.AudioBookItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.EpisodeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.PodcastItem
import com.hanan.thmanyah.takehome.presentation.model.card.BigSquareCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.GridCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.QueueCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.SquareCardUi
import com.hanan.thmanyah.takehome.presentation.util.stripHtml

fun HomeItem.toSquareUi(composeKey: String): SquareCardUi =
    SquareCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl
    )

fun HomeItem.toQueueUi(composeKey: String): QueueCardUi =
    QueueCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        description = when (this) {
            is PodcastItem -> description?.stripHtml()?.takeIf { it.isNotBlank() }
            is AudioArticleItem -> description?.stripHtml()?.takeIf { it.isNotBlank() }
            else -> null
        }
    )

fun HomeItem.toGridUi(composeKey: String): GridCardUi =
    GridCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        subtitle = when (this) {
            is EpisodeItem -> podcastName
            is AudioBookItem -> authorName
            is AudioArticleItem -> authorName
            else -> null
        }
    )

fun HomeItem.toBigSquareUi(composeKey: String): BigSquareCardUi =
    BigSquareCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        subtitle = when (this) {
            is EpisodeItem -> podcastName
            is AudioBookItem -> authorName
            is AudioArticleItem -> authorName
            is PodcastItem -> description
        }
    )