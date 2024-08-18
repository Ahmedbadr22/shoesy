package com.ab.data.model.mappers

import com.ab.data.model.dto.ReviewDto
import com.ab.data.model.entity.ReviewEntity
import com.ab.data.model.entity.ReviewWithReviewer
import com.ab.domain.model.data.Review


fun ReviewDto.toEntity(shoeId: Int) = ReviewEntity(id, user.email, rating, review, shoeId)

fun List<ReviewDto>.toEntities(shoeId: Int) = map { reviewDto -> reviewDto.toEntity(shoeId) }

fun List<ReviewDto>.getUsers() = map { reviewDto -> reviewDto.user }

fun ReviewWithReviewer.toDomain() = Review(reviewEntity.id, reviewerEntity.toDomain(), reviewEntity.rating, reviewEntity.review)

fun List<ReviewWithReviewer>.toDomainList() = map(ReviewWithReviewer::toDomain)