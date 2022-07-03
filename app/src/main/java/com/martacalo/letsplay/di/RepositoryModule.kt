package com.martacalo.letsplay.di

import com.martacalo.letsplay.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository =
        searchRepository

    @Provides
    @Singleton
    fun provideLibraryRepository(libraryRepository: LibraryRepositoryImpl): LibraryRepository =
        libraryRepository

    @Provides
    @Singleton
    fun provideGameDetailsRepository(gameDetailsRepository: GameDetailsRepositoryImpl): GameDetailsRepository =
        gameDetailsRepository

}
