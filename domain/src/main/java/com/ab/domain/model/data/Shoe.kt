package com.ab.domain.model.data

data class Shoe(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val quantity: Int,
    val sizes: List<Int>,
    val colors: List<Color>,
    val image: String,
    val brand: Brand
)
