package com.ab.shoesy.di

import com.ab.data.service.AuthenticationService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(AuthenticationService::class.java)
    }
}