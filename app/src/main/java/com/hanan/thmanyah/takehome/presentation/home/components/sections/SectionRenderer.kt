package com.hanan.thmanyah.takehome.presentation.home.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.domain.model.ContentType
import com.hanan.thmanyah.takehome.domain.model.SectionLayout
import com.hanan.thmanyah.takehome.presentation.home.components.audioarticle.AudioArticleSquareCard
import com.hanan.thmanyah.takehome.presentation.home.components.audiobook.BigSquareAudioBookCard
import com.hanan.thmanyah.takehome.presentation.home.components.episode.EpisodeGridCard
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastSquareCard
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.queue.PodcastQueueCard
import com.hanan.thmanyah.takehome.presentation.home.model.AudioArticleUi
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
            when (section.contentType) {
                ContentType.PODCAST -> {
                    val podcasts = section.items.filterIsInstance<PodcastUi>()
                    if (podcasts.isNotEmpty()) {
                        HorizontalSection(
                            title = section.title,
                            itemsCount = podcasts.size
                        ) { index ->
                            PodcastSquareCard(
                                item = podcasts[index]
                            )
                        }
                    }
                }

                ContentType.AUDIO_ARTICLE -> {
                    val articles = section.items.filterIsInstance<AudioArticleUi>()
                    if (articles.isNotEmpty()) {
                        HorizontalSection(
                            title = section.title,
                            itemsCount = articles.size
                        ) { index ->
                            AudioArticleSquareCard(item = articles[index])
                        }
                    }
                }

                else -> Unit
            }
        }

        SectionLayout.QUEUE -> {
            val podcasts = section.items.filterIsInstance<PodcastUi>()
            if (podcasts.isNotEmpty()) {
                HorizontalSection(
                    title = section.title,
                    itemsCount = podcasts.size
                ) { index ->
                    PodcastQueueCard(
                        item = podcasts[index]
                    )
                }
            }
        }

        SectionLayout.TWO_LINES_GRID -> {
            val episodes = section.items.filterIsInstance<EpisodeUi>()
            if (episodes.isNotEmpty()) {
                HorizontalGridSection(
                    title = section.title,
                    items = episodes,
                    rows = 2,
                    itemHeight = 86.dp,
                    key = { index, item -> "${item.id}-$index" }
                ) { episode ->
                    EpisodeGridCard(item = episode)
                }
            }
        }

        SectionLayout.BIG_SQUARE -> {
            val audioBooks = section.items.filterIsInstance<AudioBookUi>()
            if (audioBooks.isNotEmpty()) {
                BigSquareSection(
                    title = section.title,
                    items = audioBooks,
                    itemWidth = 220.dp,
                    key = { index, item -> "${item.id}-$index" }
                ) { item ->
                    BigSquareAudioBookCard(item = item)
                }
            }
        }

        else -> Unit
    }
}