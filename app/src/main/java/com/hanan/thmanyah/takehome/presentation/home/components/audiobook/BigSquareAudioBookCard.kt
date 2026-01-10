package com.hanan.thmanyah.takehome.presentation.home.components.audiobook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.model.AudioBookUi
import com.hanan.thmanyah.takehome.presentation.ui.components.image.ContentCover
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodySmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleMediumText

@Composable
fun BigSquareAudioBookCard(
    item: AudioBookUi,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.35f)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.30f)
        )
    ) {
        Column {
            ContentCover(
                imageUrl = item.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .heightIn(min = 44.dp)
            ) {
                TitleMediumText(
                    text = item.title,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                )

                val subtitle = listOfNotNull(
                    item.authorName?.takeIf { it.isNotBlank() },
                    item.duration,
                    item.release
                ).joinToString(" • ").takeIf { it.isNotBlank() }

                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    BodySmallText(
                        text = subtitle,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    )
                }
            }
        }
    }
}