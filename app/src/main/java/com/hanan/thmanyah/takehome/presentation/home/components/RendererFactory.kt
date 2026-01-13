package com.hanan.thmanyah.takehome.presentation.home.components

import androidx.compose.runtime.Composable
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionLayout
import com.hanan.thmanyah.takehome.presentation.home.model.BigSquareCardUi
import com.hanan.thmanyah.takehome.presentation.home.model.GridCardUi
import com.hanan.thmanyah.takehome.presentation.home.model.HomeCardUi
import com.hanan.thmanyah.takehome.presentation.home.model.QueueCardUi
import com.hanan.thmanyah.takehome.presentation.home.model.SquareCardUi
import com.hanan.thmanyah.takehome.presentation.home.rendering.LayoutRenderer
import com.hanan.thmanyah.takehome.presentation.home.rendering.card.BigSquareCard
import com.hanan.thmanyah.takehome.presentation.home.rendering.card.GridCard
import com.hanan.thmanyah.takehome.presentation.home.rendering.card.QueueCard
import com.hanan.thmanyah.takehome.presentation.home.rendering.card.SquareCard
import com.hanan.thmanyah.takehome.presentation.home.rendering.layouts.BigSquareRowLayoutRenderer
import com.hanan.thmanyah.takehome.presentation.home.rendering.layouts.HorizontalRowLayoutRenderer
import com.hanan.thmanyah.takehome.presentation.home.rendering.layouts.TwoLinesGridLayoutRenderer

object RendererFactory {

    fun layoutRenderer(layout: SectionLayout): LayoutRenderer? =
        when (layout) {
            SectionLayout.SQUARE -> HorizontalRowLayoutRenderer
            SectionLayout.QUEUE -> HorizontalRowLayoutRenderer
            SectionLayout.TWO_LINES_GRID -> TwoLinesGridLayoutRenderer
            SectionLayout.BIG_SQUARE -> BigSquareRowLayoutRenderer
            SectionLayout.UNKNOWN -> null
        }

    @Composable
    fun HomeCardRenderer(item: HomeCardUi) {
        when (item) {
            is SquareCardUi -> SquareCard(item)
            is QueueCardUi -> QueueCard(item)
            is GridCardUi -> GridCard(item)
            is BigSquareCardUi -> BigSquareCard(item)
        }
    }
}