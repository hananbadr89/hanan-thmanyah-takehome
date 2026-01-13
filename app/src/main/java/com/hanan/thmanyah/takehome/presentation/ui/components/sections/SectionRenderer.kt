package com.hanan.thmanyah.takehome.presentation.ui.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.model.section.SectionUi

@Composable
fun SectionRenderer(
    section: SectionUi,
    modifier: Modifier = Modifier
) {
    val layoutRenderer = RendererFactory.layoutRenderer(section.layout) ?: return
    if (section.items.isEmpty()) return

    layoutRenderer.Render(
        title = section.title,
        items = section.items,
        modifier = modifier,
        key = { _, item -> item.composeKey },
        itemContent = { item -> RendererFactory.CardRenderer(item) }
    )
}