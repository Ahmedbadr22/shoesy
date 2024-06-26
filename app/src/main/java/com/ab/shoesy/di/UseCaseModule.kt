package com.ab.shoesy.di

import com.ab.domain.usecases.auth.LoginUseCase
import com.ab.domain.usecases.brand.ListBrandsUseCase
import com.ab.domain.usecases.utils.EmailValidationUseCase
import com.ab.domain.usecases.utils.PasswordValidationUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::LoginUseCase)
    singleOf(::EmailValidationUseCase)
    singleOf(::PasswordValidationUseCase)
    singleOf(::ListBrandsUseCase)
}