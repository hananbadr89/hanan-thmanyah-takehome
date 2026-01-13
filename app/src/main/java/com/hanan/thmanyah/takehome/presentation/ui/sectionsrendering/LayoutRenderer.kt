package com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.model.card.CardUi

interface LayoutRenderer {
    @Composable
    fun Render(
        title: String,
        items: List<CardUi>,
        modifier: Modifier,
        key: (index: Int, item: CardUi) -> Any,
        itemContent: @Composable (item: CardUi) -> Unit
    )
}