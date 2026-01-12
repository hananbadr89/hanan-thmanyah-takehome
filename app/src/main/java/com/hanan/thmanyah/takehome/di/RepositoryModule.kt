package com.hanan.thmanyah.takehome.di

import com.hanan.thmanyah.takehome.data.home.repository.HomeRepositoryImpl
import com.hanan.thmanyah.takehome.data.search.repository.SearchRepositoryImpl
import com.hanan.thmanyah.takehome.domain.home.repository.HomeRepository
import com.hanan.thmanyah.takehome.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository
}