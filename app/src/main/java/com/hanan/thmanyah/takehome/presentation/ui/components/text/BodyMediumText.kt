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
fun BodyMediumText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun BodyMediumTextPreview() {
    ThmanyahTheme {
        BodyMediumText(
            text = "This is a medium body text used for descriptions or supporting content."
        )
    }
}