package com.hanan.thmanyah.takehome.presentation.util

fun Int?.formatAsDurationSeconds(): String? {
    val s = this ?: return null
    if (s <= 0) return null

    val hours = s / 3600
    val minutes = (s % 3600) / 60

    return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
}