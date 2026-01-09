package com.hanan.thmanyah.takehome.presentation.home.components.podcast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.components.sections.SectionHeader
import com.hanan.thmanyah.takehome.presentation.home.model.PodcastUi

@Composable
fun PodcastSquareRow(
    title: String,
    items: List<PodcastUi>,
) {
    SectionHeader(title = title)

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items.size) { index ->
            PodcastSquareCard(
                item = items[index],
                modifier = Modifier
            )
        }
    }
}