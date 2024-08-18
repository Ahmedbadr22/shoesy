package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab.core.constants.DB.COLOR_TABLE
import com.ab.data.model.entity.ColorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(colorEntity: ColorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(colorEntities: List<ColorEntity>)

    @Query("SELECT * FROM $COLOR_TABLE")
    suspend fun listAll(): List<ColorEntity>

    @Query("SELECT * FROM $COLOR_TABLE")
    fun listAllAsFlow(): Flow<List<ColorEntity>>

    @Delete
    suspend fun delete(colorEntity: ColorEntity)

    @Delete
    suspend fun delete(colorEntities: List<ColorEntity>)
}