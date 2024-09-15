package com.ab.shoesy.ui.screen.main.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Favorites : Screen()

    @Serializable
    data object Cart : Screen()

    @Serializable
    data object Profile : Screen()

    @Serializable
    data class Brand(val id: Int, val name: String, val image: String) : Screen()

    @Serializable
    data object Brands : Screen()

    @Serializable
    data class ShoeDetail(val shoeId: Int) : Screen()

    @Serializable
    data class ErrorDialog(val message: Int) : Screen()
}