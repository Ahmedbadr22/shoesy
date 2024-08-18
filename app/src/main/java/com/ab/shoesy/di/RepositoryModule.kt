package com.ab.shoesy.di

import com.ab.data.repository.AuthenticationRepositoryImpl
import com.ab.data.repository.BrandRepositoryImpl
import com.ab.data.repository.CartRepositoryImpl
import com.ab.data.repository.ColorRepositoryImpl
import com.ab.data.repository.ProductRepositoryImpl
import com.ab.domain.repository.AuthenticationRepository
import com.ab.domain.repository.BrandRepository
import com.ab.domain.repository.CartRepository
import com.ab.domain.repository.ColorRepository
import com.ab.domain.repository.ProductRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) { bind<AuthenticationRepository>() }
    singleOf(::BrandRepositoryImpl) { bind<BrandRepository>() }
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
    singleOf(::ColorRepositoryImpl) { bind<ColorRepository>() }
    singleOf(::CartRepositoryImpl) { bind<CartRepository>() }
}