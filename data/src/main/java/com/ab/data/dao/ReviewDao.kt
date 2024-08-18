package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ab.data.model.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reviewEntity: ReviewEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reviewEntities: List<ReviewEntity>)
}