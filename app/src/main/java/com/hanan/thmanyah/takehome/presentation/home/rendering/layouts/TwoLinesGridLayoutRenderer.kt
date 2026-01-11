package com.hanan.thmanyah.takehome.presentation.home.rendering.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.hanan.thmanyah.takehome.presentation.home.components.section.SectionHeader
import com.hanan.thmanyah.takehome.presentation.home.model.GridCardUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeCardUi
import com.hanan.thmanyah.takehome.presentation.home.rendering.LayoutRenderer

object TwoLinesGridLayoutRenderer : LayoutRenderer {

    @Composable
    override fun Render(
        title: String,
        items: List<HomeCardUi>,
        modifier: Modifier,
        key: (index: Int, item: HomeCardUi) -> Any,
        itemContent: @Composable (item: HomeCardUi) -> Unit
    ) {
        val gridItems = items.filterIsInstance<GridCardUi>()
        if (gridItems.isEmpty()) return

        val rows = 2
        val itemHeight = 86.dp
        val spacing = 12.dp

        val gridHeight = (rows * itemHeight) + ((rows - 1) * spacing)

        Column(modifier = modifier) {
            SectionHeader(title = title)

            LazyHorizontalGrid(
                rows = GridCells.Fixed(rows),
                modifier = Modifier.height(gridHeight),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalArrangement = Arrangement.spacedBy(spacing),
            ) {
                itemsIndexed(
                    items = gridItems,
                    key = { index, item -> key(index, item) }
                ) { _, item ->
                    itemContent(item)
                }
            }
        }
    }
}