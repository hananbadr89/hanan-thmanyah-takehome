package com.hanan.thmanyah.takehome.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HomeApiQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SearchApiQualifier