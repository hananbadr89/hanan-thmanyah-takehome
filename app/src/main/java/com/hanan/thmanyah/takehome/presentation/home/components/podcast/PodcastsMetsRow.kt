package com.hanan.thmanyah.takehome.presentation.home.components.podcast

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.hanan.thmanyah.takehome.presentation.ui.components.text.LabelSmallText

@Composable
fun PodcastMetaRow(
    episodes: String?,
    duration: String?,
    modifier: Modifier = Modifier
) {
    val parts = buildList {
        add(episodes)
        add(duration)
    }

    if (parts.isEmpty()) return

    LabelSmallText(
        text = parts.joinToString(" • "),
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    )
}