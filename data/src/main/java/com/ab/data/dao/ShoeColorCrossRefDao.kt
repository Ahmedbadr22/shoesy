package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab.core.constants.DB.SHOE_COLOR_TABLE
import com.ab.data.model.entity.ShoeColorsCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoeColorCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoeColorsCrossRef: ShoeColorsCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoeColorsCrossRefEntities: List<ShoeColorsCrossRef>)

    @Query("SELECT * FROM $SHOE_COLOR_TABLE")
    suspend fun listAll(): List<ShoeColorsCrossRef>

    @Query("SELECT * FROM $SHOE_COLOR_TABLE")
    fun listAllAsFlow(): Flow<List<ShoeColorsCrossRef>>

    @Delete
    suspend fun delete(shoeColorsCrossRef: ShoeColorsCrossRef)

    @Delete
    suspend fun delete(shoeColorsCrossRef: List<ShoeColorsCrossRef>)
}