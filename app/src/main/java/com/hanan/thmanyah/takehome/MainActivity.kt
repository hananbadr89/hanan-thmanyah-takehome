package com.hanan.thmanyah.takehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hanan.thmanyah.takehome.presentation.home.HomeRoute
import com.hanan.thmanyah.takehome.presentation.ui.theme.ThmanyahTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThmanyahTheme {
                HomeRoute()
            }
        }
    }
}