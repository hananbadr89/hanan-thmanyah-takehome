package com.hanan.thmanyah.takehome.presentation.home.components.audioarticle

import androidx.compose.runtime.Composable
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastSquareCard
import com.hanan.thmanyah.takehome.presentation.home.model.AudioArticleUi
import com.hanan.thmanyah.takehome.presentation.home.model.PodcastUi

@Composable
fun AudioArticleSquareCard(
    item: AudioArticleUi
) {
    PodcastSquareCard(
        item = PodcastUi(
            id = item.id,
            title = item.title,
            imageUrl = item.imageUrl,
            episodes = null,
            duration = item.duration,
            description = item.description
        )
    )
}