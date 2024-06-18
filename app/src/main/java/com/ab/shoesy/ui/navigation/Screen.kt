package com.ab.shoesy.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object SPLASH : Screen()

    @Serializable
    data object LOGIN : Screen()

    @Serializable
    data object REGISTRATION : Screen()


    @Serializable
    data class ErrorDialog(val message: Int) : Screen()
}