package com.hanan.thmanyah.takehome.presentation.ui.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClearableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search…",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Search,
    onImeAction: (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)?
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        placeholder = { BodyMediumText(placeholder) },
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (value.isNotBlank()) {
                IconButton(onClick = onClear) {
                    Icon(Icons.Default.Close, contentDescription = null)
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onSearch = { onImeAction?.invoke() },
            onDone = { onImeAction?.invoke() }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ClearableTextField_Stateful_Preview() {
    MaterialTheme {
        var text by remember { mutableStateOf("Search") }

        ClearableTextField(
            value = text,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            onValueChange = { text = it },
            onClear = { text = "" },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}