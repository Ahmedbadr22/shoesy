package com.ab.shoesy.di

import com.ab.domain.usecases.auth.LoginUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::LoginUseCase)
}