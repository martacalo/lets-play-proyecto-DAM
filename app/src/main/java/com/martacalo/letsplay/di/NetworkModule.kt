package com.martacalo.letsplay.di

import com.martacalo.letsplay.data.remote.api.ApiHelper
import com.martacalo.letsplay.data.remote.api.ApiHelperImpl
import com.martacalo.letsplay.data.remote.api.AtlasService
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseUrl() = "https://api.boardgameatlas.com/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient() =
            OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideRfc3339DateJsonAdapter(): Rfc3339DateJsonAdapter = Rfc3339DateJsonAdapter()

    @Provides
    @Singleton
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory,
        rfc3339DateJsonAdapter: Rfc3339DateJsonAdapter
    ): Moshi =
        Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .add(Date::class.java, rfc3339DateJsonAdapter)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AtlasService = retrofit.create(AtlasService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}
