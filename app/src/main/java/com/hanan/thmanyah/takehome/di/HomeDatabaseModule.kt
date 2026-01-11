package com.hanan.thmanyah.takehome.di

import android.content.Context
import androidx.room.Room
import com.hanan.thmanyah.takehome.data.home.local.ThmanyahDatabase
import com.hanan.thmanyah.takehome.data.home.local.dao.HomeCacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ThmanyahDatabase =
        Room.databaseBuilder(
            context,
            ThmanyahDatabase::class.java,
            "thmanyah.db"
        ).build()

    @Provides
    fun provideHomeDao(db: ThmanyahDatabase): HomeCacheDao =
        db.homeCacheDao()
}