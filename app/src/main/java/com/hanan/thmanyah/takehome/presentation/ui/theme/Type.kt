package com.hanan.thmanyah.takehome.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.hanan.thmanyah.takehome.R

val ThmanyahFontFamily = FontFamily(
    Font(R.font.ibm_plex_sans_arabic_regular, FontWeight.Normal),
    Font(R.font.ibm_plex_sans_arabic_medium, FontWeight.Medium),
    Font(R.font.ibm_plex_sans_arabic_semi_bold, FontWeight.SemiBold),
    Font(R.font.ibm_plex_sans_arabic_bold, FontWeight.Bold),
    Font(R.font.ibm_plex_sans_arabic_light, FontWeight.Light),
    Font(R.font.ibm_plex_sans_arabic_extra_light, FontWeight.ExtraLight),
    Font(R.font.ibm_plex_sans_arabic_thin, FontWeight.Thin)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = ThmanyahFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),

    titleMedium = TextStyle(
        fontFamily = ThmanyahFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = ThmanyahFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    labelSmall = TextStyle(
        fontFamily = ThmanyahFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)