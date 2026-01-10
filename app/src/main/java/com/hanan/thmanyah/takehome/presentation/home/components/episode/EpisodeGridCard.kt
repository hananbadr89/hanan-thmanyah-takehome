package com.hanan.thmanyah.takehome.presentation.home.components.episode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.model.EpisodeUi
import com.hanan.thmanyah.takehome.presentation.ui.components.image.ContentImage
import com.hanan.thmanyah.takehome.presentation.ui.components.text.LabelSmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleMediumText

@Composable
fun EpisodeGridCard(
    item: EpisodeUi,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(280.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp)
        ) {
            ContentImage(
                url = item.imageUrl,
                modifier = Modifier
                    .size(86.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TitleMediumText(
                    text = item.title,
                    maxLines = 1
                )

                Spacer(Modifier.height(6.dp))

                item.podcastName?.let {
                    LabelSmallText(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.70f)
                    )
                }

                val meta = listOfNotNull(item.duration, item.release)
                    .joinToString(" • ")
                    .takeIf { it.isNotBlank() }

                meta?.let {
                    Spacer(Modifier.height(4.dp))
                    LabelSmallText(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.70f)
                    )
                }
            }
        }
    }
}