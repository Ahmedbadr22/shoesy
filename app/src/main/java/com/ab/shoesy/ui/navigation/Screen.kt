package com.ab.shoesy.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Main : Screen()

    @Serializable
    data class Brand(val id: Int, val name: String, val image: String) : Screen()

    @Serializable
    data object Brands : Screen()

    @Serializable
    data class ShoeDetail(val shoeId: Int) : Screen()


    @Serializable
    data class ErrorDialog(val message: Int) : Screen()
}