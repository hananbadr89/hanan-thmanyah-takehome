package com.hanan.thmanyah.takehome.presentation.home.rendering.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.components.section.SectionHeader
import com.hanan.thmanyah.takehome.presentation.home.model.HomeCardUi
import com.hanan.thmanyah.takehome.presentation.home.rendering.LayoutRenderer

object BigSquareRowLayoutRenderer : LayoutRenderer {

    @Composable
    override fun Render(
        title: String,
        items: List<HomeCardUi>,
        modifier: Modifier,
        key: (index: Int, item: HomeCardUi) -> Any,
        itemContent: @Composable (item: HomeCardUi) -> Unit
    ) {
        val itemWidth = 220.dp

        Column(modifier = modifier) {
            SectionHeader(title = title)

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                itemsIndexed(
                    items = items,
                    key = { index, item -> key(index, item) }
                ) { _, item ->
                    androidx.compose.foundation.layout.Box(Modifier.width(itemWidth)) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}