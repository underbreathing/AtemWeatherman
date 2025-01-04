package com.sheverdyaevartem.artemweatherman

import android.app.Application
import com.sheverdyaevartem.artemweatherman.di.dataModule
import com.sheverdyaevartem.artemweatherman.di.interactorsModule
import com.sheverdyaevartem.artemweatherman.di.repositoryModule
import com.sheverdyaevartem.artemweatherman.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule, dataModule, repositoryModule, interactorsModule
            )
        }
    }
}