package com.ab.data.model.dto

data class ShoeDto(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val quantity: Int,
    val sizes: List<Int>,
    val colors: List<ColorDto>,
    val image: String,
    val brand: BrandDto,
    val isFavorite: Boolean,
    val reviews: List<ReviewDto>
)
