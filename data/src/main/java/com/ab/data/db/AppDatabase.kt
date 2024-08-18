package com.ab.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ab.data.dao.BrandDao
import com.ab.data.dao.CartDao
import com.ab.data.dao.ColorDao
import com.ab.data.dao.ReviewDao
import com.ab.data.dao.ShoeColorCrossRefDao
import com.ab.data.dao.ShoeDao
import com.ab.data.dao.ReviewerDao
import com.ab.data.model.entity.BrandEntity
import com.ab.data.model.entity.CartEntity
import com.ab.data.model.entity.ColorEntity
import com.ab.data.model.entity.ReviewEntity
import com.ab.data.model.entity.ShoeColorsCrossRef
import com.ab.data.model.entity.ShoeEntity
import com.ab.data.model.entity.ReviewerEntity

@Database(
    entities = [
        BrandEntity::class,
        ColorEntity::class,
        ShoeEntity::class,
        ShoeColorsCrossRef::class,
        ReviewerEntity::class,
        ReviewEntity::class,
        CartEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBrandDao() : BrandDao
    abstract fun getColorDao() : ColorDao
    abstract fun getShoeDao() : ShoeDao
    abstract fun getShoeColorCrossRefDao() : ShoeColorCrossRefDao
    abstract fun getReviewerDao() : ReviewerDao
    abstract fun getReviewDao() : ReviewDao
    abstract fun getCartDao() : CartDao
}