package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab.core.constants.DB.BRAND_TABLE
import com.ab.data.model.entity.BrandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(brandEntity: BrandEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(brandEntities: List<BrandEntity>)

    @Query("SELECT * FROM $BRAND_TABLE")
    suspend fun listAll(): List<BrandEntity>

    @Query("SELECT * FROM $BRAND_TABLE")
    fun listAllAsFlow(): Flow<List<BrandEntity>>

    @Delete
    suspend fun delete(brandEntity: BrandEntity)

    @Delete
    suspend fun delete(brandEntities: List<BrandEntity>)
}