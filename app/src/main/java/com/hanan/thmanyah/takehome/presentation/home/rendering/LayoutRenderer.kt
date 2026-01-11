package com.hanan.thmanyah.takehome.presentation.home.rendering

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.home.model.HomeCardUi

interface LayoutRenderer {
    @Composable
    fun Render(
        title: String,
        items: List<HomeCardUi>,
        modifier: Modifier,
        key: (index: Int, item: HomeCardUi) -> Any,
        itemContent: @Composable (item: HomeCardUi) -> Unit
    )
}