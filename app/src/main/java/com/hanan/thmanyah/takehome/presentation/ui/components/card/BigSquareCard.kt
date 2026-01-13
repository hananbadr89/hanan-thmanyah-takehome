package com.hanan.thmanyah.takehome.presentation.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.model.card.BigSquareCardUi
import com.hanan.thmanyah.takehome.presentation.ui.components.image.ContentImage
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodySmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleMediumText
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun BigSquareCard(
    item: BigSquareCardUi,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ContentImage(
            url = item.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(Modifier.height(6.dp))
        TitleMediumText(text = item.title, maxLines = 1)
        item.meta?.let {
            Spacer(Modifier.height(2.dp))
            BodySmallText(
                text = it,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F12)
@Composable
private fun BigSquareCardPreview() {
    ThmanyahTheme {
        BigSquareCard(
            item = BigSquareCardUi(
                id = "1",
                composeKey = "1",
                title = "The Art of War",
                imageUrl = "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228",
                meta = "Sun Tzu · Audiobook"
            ),
            modifier = Modifier
                .padding(16.dp)
                .width(220.dp)
        )
    }
}