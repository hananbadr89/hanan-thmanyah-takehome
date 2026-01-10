package com.hanan.thmanyah.takehome.presentation.home.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.components.audiobook.BigSquareAudioBookCard
import com.hanan.thmanyah.takehome.presentation.home.model.AudioBookUi

@Composable
fun BigSquareAudioBookRow(
    title: String,
    items: List<AudioBookUi>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionHeader(title = title)

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            itemsIndexed(
                items,
                key = { index, item -> "${item.id}-$index" }
            ) { _, item ->
                BigSquareAudioBookCard(
                    item = item,
                    modifier = Modifier.width(220.dp)
                )
            }
        }
    }
}