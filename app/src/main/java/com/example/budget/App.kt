package com.example.budget

import android.app.Application
import com.example.budget.di.apiModule
import com.example.budget.di.dBModule
import com.example.budget.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@App)
            modules(module, apiModule, dBModule)
        }
    }
}