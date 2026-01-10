package com.hanan.thmanyah.takehome.presentation.home.components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> HorizontalGridSection(
    title: String,
    items: List<T>,
    modifier: Modifier = Modifier,
    rows: Int = 2,
    itemHeight: Dp,
    verticalSpacing: Dp = 12.dp,
    horizontalSpacing: Dp = 12.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    key: ((index: Int, item: T) -> Any)? = null,
    itemContent: @Composable (item: T) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
        ,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionHeader(title = title)

        val gridHeight = (itemHeight * rows) + (verticalSpacing * (rows - 1))

        LazyHorizontalGrid(
            rows = GridCells.Fixed(rows),
            modifier = Modifier.height(gridHeight),
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
            verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        ) {
            if (key != null) {
                itemsIndexed(items, key = { index, item -> key(index, item) }) { _, item ->
                    itemContent(item)
                }
            } else {
                items(items) { item ->
                    itemContent(item)
                }
            }
        }
    }
}