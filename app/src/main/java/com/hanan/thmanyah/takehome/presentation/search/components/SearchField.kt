package com.hanan.thmanyah.takehome.presentation.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanan.thmanyah.takehome.presentation.ui.components.text.ClearableTextField

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier
) {
    ClearableTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        onClear = onClear,
        placeholder = "Search…",
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchFieldPreview() {
    var text by remember { mutableStateOf("Podcast") }

    SearchField(
        value = text,
        onValueChange = { text = it },
        onClear = { text = "" },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}