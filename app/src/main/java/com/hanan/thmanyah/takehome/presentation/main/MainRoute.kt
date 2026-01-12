package com.hanan.thmanyah.takehome.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.hanan.thmanyah.takehome.presentation.home.HomeRoute
import com.hanan.thmanyah.takehome.presentation.search.SearchRoute
import com.hanan.thmanyah.takehome.presentation.ui.components.bottombar.BottomBar

@Composable
fun MainTabsRoute() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val selectedTab = when (currentRoute) {
        MainTabRoute.Search.route -> MainTabRoute.Search
        else -> MainTabRoute.Home
    }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            1f to Color.Black.copy(alpha = 0.25f)
                        )
                    )
            ) {
                BottomBar(
                    selected = selectedTab,
                    onSelect = { tab ->
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = MainTabRoute.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            composable(MainTabRoute.Home.route) {
                HomeRoute()
            }
            composable(MainTabRoute.Search.route) {
                SearchRoute()
            }
        }
    }
}