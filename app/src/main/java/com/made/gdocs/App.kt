package com.made.gdocs

import android.app.Application
import com.made.core.di.dataBaseModule
import com.made.core.di.networkModule
import com.made.core.di.repositoryModule
import com.made.gdocs.di.useCaseModule
import com.made.gdocs.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    dataBaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}