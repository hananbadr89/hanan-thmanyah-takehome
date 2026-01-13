package com.hanan.thmanyah.takehome.presentation.mapper

import com.hanan.thmanyah.takehome.domain.common.model.model.item.AudioArticleItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.AudioBookItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.EpisodeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.HomeItem
import com.hanan.thmanyah.takehome.domain.common.model.model.item.PodcastItem
import com.hanan.thmanyah.takehome.presentation.util.formatAsDurationSeconds
import com.hanan.thmanyah.takehome.presentation.util.stripHtml

fun HomeItem.durationMeta(): String? = when (this) {
    is PodcastItem -> durationSec.formatAsDurationSeconds()
    is EpisodeItem -> durationSec.formatAsDurationSeconds()
    is AudioBookItem -> durationSec.formatAsDurationSeconds()
    is AudioArticleItem -> durationSec.formatAsDurationSeconds()
}

fun PodcastItem.podcastMeta(): String? {
    val eps = episodeCount?.toIntOrNull()?.let { "$it eps" }
    val dur = durationSec.formatAsDurationSeconds()
    return joinMeta(eps, dur)
}

fun HomeItem.primaryMeta(): String? = when (this) {
    is EpisodeItem -> podcastName
    is AudioBookItem -> authorName
    is AudioArticleItem -> authorName
    is PodcastItem -> durationSec.formatAsDurationSeconds()
}?.takeIf { it.isNotBlank() }

fun HomeItem.cleanedDescription(): String? =
    when (this) {
        is PodcastItem -> description
        is EpisodeItem -> description
        is AudioBookItem -> description
        is AudioArticleItem -> description
    }?.stripHtml()
        ?.takeIf { it.isNotBlank() }

fun joinMeta(vararg parts: String?): String? {
    val cleaned = parts.filterNotNull().map { it.trim() }.filter { it.isNotBlank() }
    return cleaned.takeIf { it.isNotEmpty() }?.joinToString(" • ")
}