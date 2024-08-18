package com.ab.data.model.dto

import com.google.gson.annotations.SerializedName

data class MasterDataDto(
    val shoes: List<ShoeDto>,
    val brands: List<BrandDto>,
    val colors: List<ColorDto>,
    @SerializedName("cart_items")
    val cartItems: List<CartDto>
)
