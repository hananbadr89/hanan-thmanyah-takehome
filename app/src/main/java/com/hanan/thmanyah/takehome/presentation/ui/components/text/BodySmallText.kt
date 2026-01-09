package com.hanan.thmanyah.takehome.presentation.ui.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun BodySmallText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun BodySmallTextPreview() {
    ThmanyahTheme {
        BodySmallText(
            text = "This is a short description that explains what this podcast is about."
        )
    }
}