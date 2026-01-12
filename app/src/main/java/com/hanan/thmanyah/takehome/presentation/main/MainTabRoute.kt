package com.hanan.thmanyah.takehome.presentation.main

sealed class MainTabRoute(val route: String) {
    data object Home : MainTabRoute("tab_home")
    data object Search : MainTabRoute("tab_search")
}