package com.hanan.thmanyah.takehome.presentation.ui.components.sections

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleLargeText

@Composable
fun SectionHeader(
    title: String
) {
    TitleLargeText(
        text = title,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}