package com.hanan.thmanyah.takehome.presentation.home.rendering.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.home.model.SquareCardUi
import com.hanan.thmanyah.takehome.presentation.ui.components.image.ContentImage
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodySmallText
import com.hanan.thmanyah.takehome.presentation.ui.components.text.TitleSmallText
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun SquareCard(item: SquareCardUi, modifier: Modifier = Modifier) {
    Column(modifier = modifier.width(140.dp)) {
        ContentImage(
            url = item.imageUrl,
            modifier = Modifier
                .width(140.dp)
                .height(100.dp)
        )

        Spacer(Modifier.height(6.dp))
        TitleSmallText(text = item.title, maxLines = 1)

        item.subtitle?.let {
            Spacer(Modifier.height(2.dp))
            BodySmallText(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFF0E0F12
)
@Composable
private fun SquareCardPreview() {
    ThmanyahTheme {
        SquareCard(
            item = SquareCardUi(
                id = "square_1",
                composeKey = "square_1",
                title = "The Big Listen",
                imageUrl = "https://media.npr.org/assets/img/2018/08/03/npr_tbl_podcasttile_sq-284e5618e2b2df0f3158b076d5bc44751e78e1b6.jpg",
                subtitle = "90 eps · 2h 5m"
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}