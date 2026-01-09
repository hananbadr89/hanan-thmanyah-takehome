package com.hanan.thmanyah.takehome.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.domain.model.SectionLayout
import com.hanan.thmanyah.takehome.presentation.home.components.podcast.PodcastSquareRow
import com.hanan.thmanyah.takehome.presentation.home.components.sections.SectionRenderer
import com.hanan.thmanyah.takehome.presentation.home.model.HomeSectionUi

@Composable
fun HomeSection(
    section: HomeSectionUi
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionRenderer(
            section = section
        )
    }
}