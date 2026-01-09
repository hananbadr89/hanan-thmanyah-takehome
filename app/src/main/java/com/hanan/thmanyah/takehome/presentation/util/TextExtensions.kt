package com.hanan.thmanyah.takehome.presentation.util

private val htmlRegex = Regex("<[^>]*>")

fun String.stripHtml(): String =
    replace(htmlRegex, "")
        .replace("&nbsp;", " ")
        .replace("&amp;", "&")
        .replace("&quot;", "\"")
        .replace("&#39;", "'")
        .trim()