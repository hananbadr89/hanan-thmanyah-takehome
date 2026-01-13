package com.hanan.thmanyah.takehome.presentation.ui.components.sections

import androidx.compose.runtime.Composable
import com.hanan.thmanyah.takehome.domain.common.model.model.section.SectionLayout
import com.hanan.thmanyah.takehome.presentation.model.card.BigSquareCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.CardUi
import com.hanan.thmanyah.takehome.presentation.model.card.GridCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.QueueCardUi
import com.hanan.thmanyah.takehome.presentation.model.card.SquareCardUi
import com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering.LayoutRenderer
import com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering.layouts.BigSquareRowLayoutRenderer
import com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering.layouts.HorizontalRowLayoutRenderer
import com.hanan.thmanyah.takehome.presentation.ui.sectionsrendering.layouts.TwoLinesGridLayoutRenderer
import com.hanan.thmanyah.takehome.presentation.ui.components.card.BigSquareCard
import com.hanan.thmanyah.takehome.presentation.ui.components.card.GridCard
import com.hanan.thmanyah.takehome.presentation.ui.components.card.QueueCard
import com.hanan.thmanyah.takehome.presentation.ui.components.card.SquareCard

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
    fun CardRenderer(item: CardUi) {
        when (item) {
            is SquareCardUi -> SquareCard(item)
            is QueueCardUi -> QueueCard(item)
            is GridCardUi -> GridCard(item)
            is BigSquareCardUi -> BigSquareCard(item)
        }
    }
}