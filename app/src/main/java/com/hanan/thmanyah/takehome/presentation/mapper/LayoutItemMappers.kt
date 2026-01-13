package com.hanan.thmanyah.takehome.presentation.mapper

import com.hanan.thmanyah.takehome.domain.common.model.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.PodcastItem
import com.hanan.thmanyah.takehome.presentation.model.card.BigSquareCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.GridCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.QueueCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.SquareCardUi

fun HomeItem.toSquareUi(composeKey: String): SquareCardUi =
    SquareCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        meta = when (this) {
            is PodcastItem -> podcastMeta()
            else -> durationMeta()
        }
    )

fun HomeItem.toQueueUi(composeKey: String): QueueCardUi =
    QueueCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        meta1 = when (this) {
            is PodcastItem -> podcastMeta()
            else -> durationMeta()
        },
        meta2 = cleanedDescription()
    )

fun HomeItem.toGridUi(composeKey: String): GridCardUi =
    GridCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        meta1 = primaryMeta(),
        meta2 = when (this) {
            is PodcastItem -> podcastMeta()
            else -> durationMeta()
        },
    )

fun HomeItem.toBigSquareUi(composeKey: String): BigSquareCardUi =
    BigSquareCardUi(
        id = id,
        composeKey = composeKey,
        title = title,
        imageUrl = imageUrl,
        meta = primaryMeta()
    )