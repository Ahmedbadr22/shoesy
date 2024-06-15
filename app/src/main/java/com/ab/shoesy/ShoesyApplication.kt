package com.ab.shoesy

import android.app.Application
import com.ab.shoesy.di.appModule
import com.ab.shoesy.di.repositoryModule
import com.ab.shoesy.di.serviceModule
import com.ab.shoesy.di.useCaseModule
import com.ab.shoesy.di.viewModelModule
import org.koin.core.context.startKoin

class ShoesyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    appModule,
                    serviceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}