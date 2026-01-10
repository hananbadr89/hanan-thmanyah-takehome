package com.hanan.thmanyah.takehome.presentation.home.components.sections

import androidx.compose.runtime.Composable
import com.hanan.thmanyah.takehome.domain.model.SectionLayout
import com.hanan.thmanyah.takehome.presentation.home.components.episode.EpisodeGrid
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastSquareRow
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.queue.PodcastQueueRow
import com.hanan.thmanyah.takehome.presentation.home.model.AudioBookUi
import com.hanan.thmanyah.takehome.presentation.home.model.EpisodeUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi
import com.hanan.thmanyah.takehome.presentation.home.model.PodcastUi

@Composable
fun SectionRenderer(
    section: HomeSectionUi
) {
    when (section.layout) {
        SectionLayout.SQUARE -> {
            val podcasts = section.items.filterIsInstance<PodcastUi>()
            if (podcasts.isNotEmpty()) {
                PodcastSquareRow(
                    title = section.title,
                    items = podcasts
                )
            }
        }

        SectionLayout.QUEUE -> {
            val podcasts = section.items.filterIsInstance<PodcastUi>()
            if (podcasts.isNotEmpty()) {
                PodcastQueueRow(
                    title = section.title,
                    items = podcasts
                )
            }
        }

        SectionLayout.TWO_LINES_GRID -> {
            val episodes = section.items.filterIsInstance<EpisodeUi>()
            if (episodes.isNotEmpty()) {
                EpisodeGrid(
                    title = section.title,
                    items = episodes
                )
            }
        }

        SectionLayout.BIG_SQUARE -> {
            val audioBook = section.items.filterIsInstance<AudioBookUi>()
            if (audioBook.isNotEmpty()) {
                BigSquareAudioBookRow(
                    title = section.title,
                    items = audioBook
                )
            }
        }

        else -> Unit
    }
}