package com.ab.shoesy.di

import android.content.Context
import androidx.room.Room
import com.ab.core.constants.API
import com.ab.core.constants.DB
import com.ab.data.db.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single<OkHttpClient> {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient().newBuilder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(logger)
            .build()
    }

    single {
        val client: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        val context: Context = get()
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB.DB_NAME,
        ).build()
    }
}