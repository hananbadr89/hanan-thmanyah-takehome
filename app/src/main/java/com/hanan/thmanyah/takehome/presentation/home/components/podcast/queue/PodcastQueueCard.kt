package com.hanan.thmanyah.takehome.presentation.home.components.podcast.queue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastCover
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastMetaRow
import com.hanan.thmanyah.takehome.presentation.home.model.PodcastUi
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodySmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleMediumText

@Composable
fun PodcastQueueCard(
    item: PodcastUi,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(18.dp)

    Card(
        modifier = modifier.width(300.dp),
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PodcastCover(
                url = item.imageUrl,
                modifier = Modifier.size(56.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TitleMediumText(
                    text = item.title,
                    maxLines = 1
                )

                PodcastMetaRow(
                    episodes = item.episodes,
                    duration = item.duration,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.70f)
                )

                if (!item.description.isNullOrBlank()) {
                    BodySmallText(
                        text = item.description,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                    )
                }
            }
        }
    }
}