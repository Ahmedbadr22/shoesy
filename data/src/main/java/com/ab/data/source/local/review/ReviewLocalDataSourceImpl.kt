package com.ab.data.source.local.review

import com.ab.data.dao.ReviewDao
import com.ab.data.model.entity.ReviewEntity
import com.ab.data.source.local.reviewer.ReviewerLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReviewLocalDataSourceImpl(
    private val reviewDao: ReviewDao
) : ReviewLocalDataSource {
    override suspend fun insert(reviewEntity: ReviewEntity) {
        withContext(Dispatchers.IO) {
            reviewDao.insert(reviewEntity)
        }
    }

    override suspend fun insert(reviewEntities: List<ReviewEntity>) {
        withContext(Dispatchers.IO) {
            reviewDao.insert(reviewEntities)
        }
    }

}