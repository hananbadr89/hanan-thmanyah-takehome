package com.hanan.thmanyah.takehome.presentation.home.mapper

import com.hanan.thmanyah.takehome.domain.model.HomeSection
import com.hanan.thmanyah.takehome.domain.model.PodcastItem
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi
import com.hanan.thmanyah.takehome.presentation.home.model.PodcastUi
import com.hanan.thmanyah.takehome.presentation.util.formatAsDurationSeconds
import com.hanan.thmanyah.takehome.presentation.util.stripHtml

fun List<HomeSection>.toHomeSectionUi(): List<HomeSectionUi> =
    mapNotNull { it.toUiOrNull() }

private fun HomeSection.toUiOrNull(): HomeSectionUi? {

    val podcasts = items
        .filterIsInstance<PodcastItem>()
        .map { it.toUi() }

    if (podcasts.isEmpty()) return null

    return HomeSectionUi(
        title = name,
        layout = type,
        items = podcasts
    )
}

private fun PodcastItem.toUi(): PodcastUi =
    PodcastUi(
        id = id,
        title = name,
        description = description?.stripHtml()?.takeIf { it.isNotBlank() },
        imageUrl = avatarUrl,
        episodes = episodeCount?.takeIf { it > 0 }?.let { "$it eps" },
        duration = duration.formatAsDurationSeconds()
    )