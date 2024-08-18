package com.ab.shoesy.di

import com.ab.data.service.AuthenticationService
import com.ab.data.service.BrandService
import com.ab.data.service.CartService
import com.ab.data.service.ColorService
import com.ab.data.service.ProductService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single<AuthenticationService> {
        val retrofit: Retrofit = get()
        retrofit.create(AuthenticationService::class.java)
    }

    single<BrandService> {
        val retrofit: Retrofit = get()
        retrofit.create(BrandService::class.java)
    }

    single<ProductService> {
        val retrofit: Retrofit = get()
        retrofit.create(ProductService::class.java)
    }

    single<ColorService> {
        val retrofit: Retrofit = get()
        retrofit.create(ColorService::class.java)
    }

    single<CartService> {
        val retrofit: Retrofit = get()
        retrofit.create(CartService::class.java)
    }
}