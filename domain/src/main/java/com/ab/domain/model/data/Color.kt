package com.ab.domain.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Color(
    val id: Int,
    val name: String,
    val hex: String
)
