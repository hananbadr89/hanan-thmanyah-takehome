package com.hanan.thmanyah.takehome.presentation.home.rendering.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.model.QueueCardUi
import com.hanan.thmanyah.takehome.presentation.ui.components.image.ContentImage
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodySmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleMediumText
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun QueueCard(
    item: QueueCardUi,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(300.dp)
            .padding(12.dp)
    ) {
        ContentImage(url = item.imageUrl, modifier = Modifier.size(56.dp))
        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            TitleMediumText(text = item.title, maxLines = 1)
            item.subtitle?.let {
                Spacer(Modifier.height(4.dp))
                BodySmallText(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            item.description?.takeIf { it.isNotBlank() }?.let {
                Spacer(Modifier.height(4.dp))
                BodySmallText(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F12)
@Composable
private fun QueueCardPreview() {
    ThmanyahTheme {
        QueueCard(
            item = QueueCardUi(
                id = "queue_1",
                composeKey = "queue_1",
                title = "The Daily",
                imageUrl = "https://media.npr.org/assets/img/2018/08/03/npr_tbl_podcasttile_sq-284e5618e2b2df0f3158b076d5bc44751e78e1b6.jpg",
                subtitle = "90 episodes · 2h 5m",
                description = "This is what the news should sound like."
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}