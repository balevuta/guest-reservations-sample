package com.ethan.disneycodechallenge.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GuestProviderModule {

    private const val GUEST_PREFS = "GuestPrefs"

    @Provides
    fun providesSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(GUEST_PREFS, Context.MODE_PRIVATE)
    }
}
