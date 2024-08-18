package com.ab.domain.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    val id: Int,
    val name: String,
    val image: String,
)
