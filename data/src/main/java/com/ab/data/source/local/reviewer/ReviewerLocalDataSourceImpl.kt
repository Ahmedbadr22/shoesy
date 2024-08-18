package com.ab.data.source.local.reviewer

import com.ab.data.dao.ReviewerDao
import com.ab.data.model.entity.ReviewerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReviewerLocalDataSourceImpl(
    private val reviewerDao: ReviewerDao
) : ReviewerLocalDataSource {
    override suspend fun insert(reviewerEntity: ReviewerEntity) {
        withContext(Dispatchers.IO) {
            reviewerDao.insert(reviewerEntity)
        }
    }

    override suspend fun insert(reviewerEntities: List<ReviewerEntity>) {
        withContext(Dispatchers.IO) {
            reviewerDao.insert(reviewerEntities)
        }
    }

}