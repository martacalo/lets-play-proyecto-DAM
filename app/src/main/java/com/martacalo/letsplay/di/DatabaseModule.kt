package com.martacalo.letsplay.di

import android.content.Context
import com.martacalo.letsplay.data.local.AppDatabase
import com.martacalo.letsplay.data.local.GamesDao
import com.martacalo.letsplay.data.local.model.LibraryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideGamesDao(appDatabase: AppDatabase): GamesDao = appDatabase.gamesDao()

    @Singleton
    @Provides
    fun provideLibraryDao(appDatabase: AppDatabase): LibraryDao = appDatabase.libraryDao()

}
