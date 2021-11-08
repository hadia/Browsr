package com.zenjob.android.browsr

import android.app.Application
import com.zenjob.android.browsr.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BrowserApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BrowserApp)
            modules(applicationModules)
        }
    }
}
