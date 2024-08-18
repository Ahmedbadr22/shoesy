package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ab.data.model.entity.ReviewerEntity

@Dao
interface ReviewerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reviewerEntity: ReviewerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntities: List<ReviewerEntity>)
}