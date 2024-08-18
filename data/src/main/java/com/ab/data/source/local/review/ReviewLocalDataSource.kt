package com.ab.data.source.local.review

import com.ab.data.model.entity.ReviewEntity

interface ReviewLocalDataSource {

    suspend fun insert(reviewEntity: ReviewEntity)
    suspend fun insert(reviewEntities: List<ReviewEntity>)
}