package com.ab.shoesy

import android.app.Application
import com.ab.shoesy.di.appModule
import org.koin.core.context.startKoin

class ShoesyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    appModule
                )
            )
        }
    }
}