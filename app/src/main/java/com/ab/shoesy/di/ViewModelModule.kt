package com.ab.shoesy.di

import com.ab.shoesy.ui.screen.brand.BrandViewModel
import com.ab.shoesy.ui.screen.cart.CartViewModel
import com.ab.shoesy.ui.screen.favorite.FavoriteViewModel
import com.ab.shoesy.ui.screen.home.HomeViewModel
import com.ab.shoesy.ui.screen.auth.screen.login.LoginViewModel
import com.ab.shoesy.ui.screen.main.MainViewModel
import com.ab.shoesy.ui.screen.shoe.ShoeViewModel
import com.ab.shoesy.ui.screen.auth.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::BrandViewModel)
    viewModelOf(::ShoeViewModel)
    viewModelOf(::FavoriteViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::CartViewModel)
}