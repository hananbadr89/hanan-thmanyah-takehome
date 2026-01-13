package com.hanan.thmanyah.takehome.presentation.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.HomeTestTags
import com.hanan.thmanyah.takehome.presentation.model.section.SectionUi
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun SectionsContent(
    sections: List<SectionUi>,
    onLoadMore: () -> Unit,
    canLoadMore: Boolean,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState, canLoadMore) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val total = layoutInfo.totalItemsCount
            lastVisible to total
        }
            .distinctUntilChanged()
            .collect { (lastVisible, total) ->
                if (!canLoadMore) return@collect

                val buffer = 3
                val shouldLoadMore = total > 0 && lastVisible >= total - 1 - buffer

                if (shouldLoadMore) {
                    onLoadMore()
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize().testTag(HomeTestTags.LIST),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = sections,
            key = { it.id }
        ) { section ->
            SectionRenderer(section)
        }
    }
}