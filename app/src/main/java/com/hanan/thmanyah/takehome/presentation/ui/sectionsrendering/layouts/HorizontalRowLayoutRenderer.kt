package com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.model.card.CardUi
import com.hanan.thmanyah.takehome.presentation.ui.components.sections.SectionHeader
import com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering.LayoutRenderer

object HorizontalRowLayoutRenderer : LayoutRenderer {

    @Composable
    override fun Render(
        title: String,
        items: List<CardUi>,
        modifier: Modifier,
        key: (index: Int, item: CardUi) -> Any,
        itemContent: @Composable (item: CardUi) -> Unit
    ) {
        Column(modifier = modifier) {
            SectionHeader(title = title)

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(
                    items = items,
                    key = { index, item -> key(index, item) }
                ) { _, item ->
                    itemContent(item)
                }
            }
        }
    }
}