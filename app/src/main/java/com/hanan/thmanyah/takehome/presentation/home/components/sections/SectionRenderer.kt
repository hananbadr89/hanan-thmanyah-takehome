package com.hanan.thmanyah.takehome.presentation.home.components.sections

import androidx.compose.runtime.Composable
import com.hanan.thmanyah.takehome.domain.model.SectionLayout
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastSquareRow
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi

@Composable
fun SectionRenderer(
    section: HomeSectionUi
) {
    when (section.layout) {
        SectionLayout.SQUARE ->
            PodcastSquareRow(section.title, section.items)

        else -> Unit
    }
}