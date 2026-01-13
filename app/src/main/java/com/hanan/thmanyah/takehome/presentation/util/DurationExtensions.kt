package com.hanan.thmanyah.takehome.presentation.util

fun Any?.formatAsDurationSeconds(): String? {
    val seconds = when (this) {
        is Int -> this
        is Long -> this.toInt()
        is Double -> this.toInt()
        is Float -> this.toInt()
        is String -> this.toDoubleOrNull()?.toInt()
        else -> null
    } ?: return null
    if (seconds <= 0) return null

    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60

    return if (hours > 0) {
        "${hours}h ${minutes}m"
    } else {
        "${minutes}m"
    }
}