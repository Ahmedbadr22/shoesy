package com.ab.shoesy.di

import com.ab.shoesy.ui.screen.main.screen.brand.BrandViewModel
import com.ab.shoesy.ui.screen.main.screen.cart.CartViewModel
import com.ab.shoesy.ui.screen.main.screen.favorite.FavoriteViewModel
import com.ab.shoesy.ui.screen.main.screen.home.HomeViewModel
import com.ab.shoesy.ui.screen.auth.screen.login.LoginViewModel
import com.ab.shoesy.ui.screen.main.screen.shoe.ShoeViewModel
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
    viewModelOf(::CartViewModel)
}