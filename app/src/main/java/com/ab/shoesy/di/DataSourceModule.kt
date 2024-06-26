package com.ab.shoesy.di

import com.ab.data.source.remote.auth.AuthDataSource
import com.ab.data.source.remote.auth.AuthRemoteDataSourceImpl
import com.ab.data.source.remote.brand.BrandRemoteDataSource
import com.ab.data.source.remote.brand.BrandRemoteDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::AuthRemoteDataSourceImpl) { bind<AuthDataSource>() }
    singleOf(::BrandRemoteDataSourceImpl) { bind<BrandRemoteDataSource>() }
}