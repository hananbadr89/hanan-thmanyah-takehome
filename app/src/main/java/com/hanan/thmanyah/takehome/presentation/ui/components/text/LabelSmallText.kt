package com.hanan.thmanyah.takehome.presentation.ui.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme

@Composable
fun LabelSmallText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun LabelSmallTextPreview() {
    ThmanyahTheme {
        LabelSmallText(
            text = "NEW"
        )
    }
}