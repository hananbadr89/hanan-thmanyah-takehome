package com.hanan.thmanyah.takehome.presentation.ui.components.bottombar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hanan.thmanyah.takehome.presentation.main.MainTabRoute

@Composable
fun BottomBar(
    selected: MainTabRoute,
    onSelect: (MainTabRoute) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            modifier = Modifier.weight(1f),
            selected = selected == MainTabRoute.Home,
            icon = Icons.Default.Home,
            onClick = { onSelect(MainTabRoute.Home) }
        )

        BottomBarItem(
            modifier = Modifier.weight(1f),
            selected = selected == MainTabRoute.Search,
            icon = Icons.Default.Search,
            onClick = { onSelect(MainTabRoute.Search) }
        )
    }
}