package com.ethan.disneycodechallenge.di

import android.content.Context
import android.content.res.Resources
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreProviderModule {

    @Provides
    @Singleton
    fun providesResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
