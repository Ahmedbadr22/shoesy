package com.ab.data.model.mappers

import com.ab.data.model.dto.ReviewerDto
import com.ab.data.model.entity.ReviewerEntity
import com.ab.domain.model.data.Reviewer


fun ReviewerDto.toEntity() = ReviewerEntity(email, profileImage, fullName)

fun List<ReviewerDto>.toEntities() = map { reviewerDto -> reviewerDto.toEntity() }


fun ReviewerEntity.toDomain() = Reviewer(email, profileImage, fullName)

fun List<ReviewerEntity>.toDomainList() = map(ReviewerEntity::toDomain)

