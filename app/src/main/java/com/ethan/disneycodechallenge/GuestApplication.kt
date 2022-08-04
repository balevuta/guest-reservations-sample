package com.ethan.disneycodechallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GuestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}