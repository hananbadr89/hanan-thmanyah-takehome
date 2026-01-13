package com.hanan.thmanyah.takehome.presentation.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.model.card.GridCardUi
import com.hanan.thmanyah.takehome.presentation.ui.components.image.ContentImage
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodySmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleMediumText
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun GridCard(
    item: GridCardUi,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(86.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.04f))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContentImage(
            url = item.imageUrl,
            modifier = Modifier.size(56.dp)
        )

        Spacer(Modifier.width(12.dp))

        Column {
            TitleMediumText(
                text = item.title,
                maxLines = 1
            )

            item.meta1?.let {
                Spacer(Modifier.height(2.dp))
                BodySmallText(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                )
            }

            item.meta2?.let {
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
private fun GridCardPreview() {
    ThmanyahTheme {
        GridCard(
            item = GridCardUi(
                id = "grid_1",
                composeKey = "grid_1",
                title = "NPR News: 05-21-2024 10AM EDT",
                imageUrl = "https://media.npr.org/assets/img/2023/03/01/npr-news-now_square.png",
                meta1 = "NPR News Now · 5m",
                meta2 = "90 eps · 2h 5m"
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}