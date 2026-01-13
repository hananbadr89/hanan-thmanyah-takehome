package com.hanan.thmanyah.takehome.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodyMediumText

@Composable
fun SearchEmptyHint(modifier: Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        BodyMediumText(
            text = "Start typing to search",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SearchEmptyHintPreview() {
    MaterialTheme {
        SearchEmptyHint(Modifier)
    }
}