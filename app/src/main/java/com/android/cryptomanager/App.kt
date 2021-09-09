package com.android.cryptomanager

import android.app.Application
import com.android.cryptomanager.home.presentation.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(homeModule))
        }
    }
}
