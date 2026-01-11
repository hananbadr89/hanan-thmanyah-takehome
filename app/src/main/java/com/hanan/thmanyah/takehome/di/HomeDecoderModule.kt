package com.hanan.thmanyah.takehome.di

import com.hanan.thmanyah.takehome.data.home.mapper.decoder.ContentDecoder
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDecoderModule {

    @Provides
    @Singleton
    fun provideSectionContentDecoder(
        moshi: Moshi
    ): ContentDecoder = ContentDecoder(moshi)
}