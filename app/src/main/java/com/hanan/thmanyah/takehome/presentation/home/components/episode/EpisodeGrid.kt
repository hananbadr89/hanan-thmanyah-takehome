package com.hanan.thmanyah.takehome.presentation.home.components.episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.components.sections.SectionHeader
import com.hanan.thmanyah.takehome.presentation.home.model.EpisodeUi

@Composable
fun EpisodeGrid(
    title: String,
    items: List<EpisodeUi>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionHeader(title = title)

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.height((2 * 86).dp + 12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items, key = { it.id }) { episode ->
                EpisodeGridCard(
                    item = episode
                )
            }
        }
    }
}