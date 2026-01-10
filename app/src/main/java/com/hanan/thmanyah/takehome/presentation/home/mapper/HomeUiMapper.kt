package com.hanan.thmanyah.takehome.presentation.home.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.hanan.thmanyah.takehome.domain.model.AudioBookItem
import com.hanan.thmanyah.takehome.domain.model.ContentType
import com.hanan.thmanyah.takehome.domain.model.EpisodeItem
import com.hanan.thmanyah.takehome.domain.model.HomeSection
import com.hanan.thmanyah.takehome.domain.model.PodcastItem
import com.hanan.thmanyah.takehome.presentation.home.model.AudioBookUi
import com.hanan.thmanyah.takehome.presentation.home.model.EpisodeUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeItemUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi
import com.hanan.thmanyah.takehome.presentation.home.model.PodcastUi
import com.hanan.thmanyah.takehome.presentation.util.formatAsDurationSeconds
import com.hanan.thmanyah.takehome.presentation.util.stripHtml
import com.hanan.thmanyah.takehome.presentation.util.toYearOrEmpty

@RequiresApi(Build.VERSION_CODES.O)
fun List<HomeSection>.toHomeSectionUi(): List<HomeSectionUi> =
    mapNotNull { it.toUiOrNull() }

@RequiresApi(Build.VERSION_CODES.O)
private fun HomeSection.toUiOrNull(): HomeSectionUi? {
    val uiItems: List<HomeItemUi> = when (contentType) {
        ContentType.PODCAST -> items
            .filterIsInstance<PodcastItem>()
            .map { it.toUi() }

        ContentType.EPISODE -> items
            .filterIsInstance<EpisodeItem>()
            .map { it.toUi() }

        ContentType.AUDIO_BOOK -> items
            .filterIsInstance<AudioBookItem>()
            .map { it.toUi() }

        ContentType.UNKNOWN -> emptyList()
    }

    if (uiItems.isEmpty()) return null

    return HomeSectionUi(
        title = name,
        layout = type,
        items = uiItems
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

@RequiresApi(Build.VERSION_CODES.O)
fun EpisodeItem.toUi(): EpisodeUi = EpisodeUi(
    id = id,
    title = title,
    podcastName = podcastName,
    imageUrl = imageUrl,
    duration = durationSec.formatAsDurationSeconds(),
    release = releaseDateIso?.toYearOrEmpty()
)

fun AudioBookItem.toUi(): AudioBookUi = AudioBookUi(
    id = id,
    title = title,
    imageUrl = imageUrl,
    duration = durationSec.formatAsDurationSeconds(),
    release = releaseDateIso?.toYearOrEmpty(),
    authorName = authorName
)