package com.hanan.thmanyah.takehome.presentation.home.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> BigSquareSection(
    title: String,
    items: List<T>,
    modifier: Modifier = Modifier,
    itemWidth: Dp = 220.dp,
    key: ((index: Int, item: T) -> Any)? = null,
    itemContent: @Composable (item: T) -> Unit,
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
                items = items,
                key = if (key != null) ({ index, item -> key(index, item) }) else null
            ) { _, item ->
                Box(Modifier.width(itemWidth)) {
                    itemContent(item)
                }
            }
        }
    }
}