package com.ab.shoesy.di

import com.ab.data.service.AuthenticationService
import com.ab.data.service.BrandService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(AuthenticationService::class.java)
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(BrandService::class.java)
    }
}