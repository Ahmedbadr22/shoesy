package com.ab.domain.model.data

data class CartItem(
    val id: Int,
    val shoeId: Int,
    val shoePrice: Float,
    val shoeName: String,
    val shoeBrand: Brand,
    val shoeImage: String,
    val color: Color,
    val quantity: Int,
    val size: Int
)


fun CartItem.getTotalPrice() : Float = shoePrice * quantity