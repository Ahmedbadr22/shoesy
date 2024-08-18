package com.ab.shoesy.di

import com.ab.data.source.local.brand.BrandLocalDataSource
import com.ab.data.source.local.brand.BrandLocalDataSourceImpl
import com.ab.data.source.local.cart.CartLocalDataSource
import com.ab.data.source.local.cart.CartLocalDataSourceImpl
import com.ab.data.source.local.color.ColorLocalDataSource
import com.ab.data.source.local.color.ColorLocalDataSourceImpl
import com.ab.data.source.local.review.ReviewLocalDataSource
import com.ab.data.source.local.review.ReviewLocalDataSourceImpl
import com.ab.data.source.local.shoe.ShoeLocalDataSource
import com.ab.data.source.local.shoe.ShoeLocalDataSourceImpl
import com.ab.data.source.remote.auth.AuthDataSource
import com.ab.data.source.remote.auth.AuthRemoteDataSourceImpl
import com.ab.data.source.remote.brand.BrandRemoteDataSource
import com.ab.data.source.remote.brand.BrandRemoteDataSourceImpl
import com.ab.data.source.local.reviewer.ReviewerLocalDataSource
import com.ab.data.source.local.reviewer.ReviewerLocalDataSourceImpl
import com.ab.data.source.remote.cart.CartRemoteDataSource
import com.ab.data.source.remote.cart.CartRemoteDataSourceImpl
import com.ab.data.source.remote.color.ColorRemoteDataSource
import com.ab.data.source.remote.color.ColorRemoteDataSourceImpl

import com.ab.data.source.remote.product.ProductRemoteDataSource
import com.ab.data.source.remote.product.ProductRemoteDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    // Remote
    singleOf(::AuthRemoteDataSourceImpl) { bind<AuthDataSource>() }
    singleOf(::BrandRemoteDataSourceImpl) { bind<BrandRemoteDataSource>() }
    singleOf(::ProductRemoteDataSourceImpl) { bind<ProductRemoteDataSource>() }
    singleOf(::ColorRemoteDataSourceImpl) { bind<ColorRemoteDataSource>() }
    singleOf(::CartRemoteDataSourceImpl) { bind<CartRemoteDataSource>() }


    // Local
    singleOf(::BrandLocalDataSourceImpl) { bind<BrandLocalDataSource>() }
    singleOf(::ShoeLocalDataSourceImpl) { bind<ShoeLocalDataSource>() }
    singleOf(::ColorLocalDataSourceImpl) { bind<ColorLocalDataSource>() }
    singleOf(::ReviewerLocalDataSourceImpl) { bind<ReviewerLocalDataSource>() }
    singleOf(::ReviewLocalDataSourceImpl) { bind<ReviewLocalDataSource>() }
    singleOf(::CartLocalDataSourceImpl) { bind<CartLocalDataSource>() }
}