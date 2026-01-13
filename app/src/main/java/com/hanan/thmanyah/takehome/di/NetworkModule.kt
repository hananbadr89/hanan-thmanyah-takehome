package com.hanan.thmanyah.takehome.di

import com.hanan.thmanyah.takehome.data.home.remote.HomeRemoteDataSource
import com.hanan.thmanyah.takehome.data.search.remote.SearchRemoteDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val HOME_BASE_URL = "https://api-v2-b2sit6oh3a-uc.a.run.app/"
    private const val SEARCH_BASE_URL = "https://mock.apidog.com/m1/735111-711675-default/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            )
            .build()

    @Provides
    @Singleton
    @HomeApiQualifier
    fun provideHomeRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(HOME_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    @SearchApiQualifier
    fun provideSearchRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(SEARCH_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideHomeRemoteDataSource(
        @HomeApiQualifier retrofit: Retrofit
    ): HomeRemoteDataSource =
        retrofit.create(HomeRemoteDataSource::class.java)

    @Provides
    @Singleton
    fun provideSearchRemoteDataSource(
        @SearchApiQualifier retrofit: Retrofit
    ): SearchRemoteDataSource =
        retrofit.create(SearchRemoteDataSource::class.java)
}