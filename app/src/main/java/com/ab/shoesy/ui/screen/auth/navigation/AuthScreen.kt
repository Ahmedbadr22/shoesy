package com.ab.shoesy.ui.screen.auth.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthScreen {
    @Serializable
    data object SPLASH : AuthScreen()

    @Serializable
    data object LOGIN : AuthScreen()

    @Serializable
    data object REGISTRATION : AuthScreen()
}