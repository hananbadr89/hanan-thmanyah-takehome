package com.hanan.thmanyah.takehome.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.ui.components.text.BodyMediumText

@Composable
fun NoResults(query: String, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        BodyMediumText(
            text = "No results for \"$query\"",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NoResultsPreview() {
    MaterialTheme {
        NoResults(
            query = "podcast",
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}