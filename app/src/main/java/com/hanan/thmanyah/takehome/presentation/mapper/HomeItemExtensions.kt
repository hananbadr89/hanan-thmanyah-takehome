package com.hanan.thmanyah.takehome.presentation.mapper

fun Any?.toIntOrNull(): Int? = when (this) {
    is Int -> this
    is Double -> this.toInt()
    is Float -> this.toInt()
    is Long -> this.toInt()
    else -> null
}