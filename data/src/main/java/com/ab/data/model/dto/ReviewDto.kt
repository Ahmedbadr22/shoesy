package com.ab.data.model.dto

data class ReviewDto(
    val id: Int,
    val user: ReviewerDto,
    val rating: Int,
    val review: String
)
