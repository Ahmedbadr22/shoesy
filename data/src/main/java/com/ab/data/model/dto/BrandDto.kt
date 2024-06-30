package com.ab.data.model.dto

import com.google.gson.annotations.SerializedName

data class BrandDto(
    val id: Int,
    val name: String,
    val image: String,
    @SerializedName("stock_item_count")
    val stockItemCount: Int,
)
