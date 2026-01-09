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
fun TitleSmallText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    maxLines: Int = 1
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        softWrap = false
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleSmallTextPreview() {
    ThmanyahTheme {
        TitleSmallText(
            text = "The Big Listen: A Podcast About Podcasts and Stories Worth Hearing"
        )
    }
}