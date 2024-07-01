package com.ab.shoesy

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.ab.shoesy.di.appModule
import com.ab.shoesy.di.dataSourceModule
import com.ab.shoesy.di.repositoryModule
import com.ab.shoesy.di.serviceModule
import com.ab.shoesy.di.useCaseModule
import com.ab.shoesy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShoesyApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ShoesyApplication)
            modules(
                listOf(
                    appModule,
                    serviceModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .logger(DebugLogger()) // Enable detailed logging
            .build()
    }
}