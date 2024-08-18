package com.ab.data.source.local.reviewer

import com.ab.data.model.entity.ReviewerEntity

interface ReviewerLocalDataSource {

    suspend fun insert(reviewerEntity: ReviewerEntity)
    suspend fun insert(reviewerEntities: List<ReviewerEntity>)
}