package com.ab.domain.model.data

data class Review(
    val id: Int,
    val reviewer: Reviewer,
    val rating: Int,
    val review: String,
)
