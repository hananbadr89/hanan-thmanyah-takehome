package com.hanan.thmanyah.takehome.presentation.ui.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun TitleMediumText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 2,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleMediumTextPreview() {
    ThmanyahTheme {
        TitleMediumText(
            text = "The Big Listen: A Podcast About Podcasts and Stories Worth Hearing"
        )
    }
}