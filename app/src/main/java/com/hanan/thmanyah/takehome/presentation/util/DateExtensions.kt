package com.hanan.thmanyah.takehome.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun String.toYearOrEmpty(): String {
    return try {
        OffsetDateTime.parse(this).year.toString()
    } catch (e: Exception) {
        ""
    }
}