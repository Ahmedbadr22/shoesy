package com.ab.shoesy.di

import com.ab.data.repository.AuthenticationRepositoryImpl
import com.ab.domain.repository.AuthenticationRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) { bind<AuthenticationRepository>() }
}