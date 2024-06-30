package com.ab.shoesy.di

import com.ab.shoesy.ui.screen.brand.BrandViewModel
import com.ab.shoesy.ui.screen.home.HomeViewModel
import com.ab.shoesy.ui.screen.login.LoginViewModel
import com.ab.shoesy.ui.screen.shoe.ShoeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::BrandViewModel)
    viewModelOf(::ShoeViewModel)
}