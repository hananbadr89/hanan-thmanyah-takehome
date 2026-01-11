package com.hanan.thmanyah.takehome.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi

@Composable
fun SectionRenderer(
    section: HomeSectionUi,
    modifier: Modifier = Modifier
) {
    val layoutRenderer = RendererFactory.layoutRenderer(section.layout) ?: return
    if (section.items.isEmpty()) return

    layoutRenderer.Render(
        title = section.title,
        items = section.items,
        modifier = modifier,
        key = { _, item -> item.composeKey },
        itemContent = { item -> RendererFactory.HomeCardRenderer(item) }
    )
}