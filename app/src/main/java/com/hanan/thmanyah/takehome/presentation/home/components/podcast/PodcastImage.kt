package com.hanan.thmanyah.takehome.presentation.home.components.podcast

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PodcastCover(
    url: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(14.dp)),
        contentScale = ContentScale.Crop
    )
}