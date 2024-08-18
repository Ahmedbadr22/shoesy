package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ab.core.constants.DB.SHOE_TABLE
import com.ab.data.model.entity.ShoeEntity
import com.ab.data.model.entity.ShoeWithBrandsAndColors
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoeEntity: ShoeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoeEntities: List<ShoeEntity>)

    @Query("UPDATE $SHOE_TABLE SET isFavorite = 1 WHERE id = :id")
    suspend fun markIsFavoriteById(id: Int)

    @Query("UPDATE $SHOE_TABLE SET isFavorite = 0 WHERE id = :id")
    suspend fun markAsNotFavoriteById(id: Int)

    @Transaction
    @Query("SELECT * FROM $SHOE_TABLE  WHERE isFavorite = 1")
    fun listAllFavoritesFlow() : Flow<List<ShoeWithBrandsAndColors>>

    @Transaction
    @Query("SELECT * FROM $SHOE_TABLE ORDER BY RANDOM() LIMIT 3")
    fun listRandomShoeFlow() : Flow<List<ShoeWithBrandsAndColors>>

    @Query("SELECT COUNT(*) FROM $SHOE_TABLE  WHERE isFavorite = 1")
    fun getFavoritesCountFlow() : Flow<Int>

    @Transaction
    @Query("SELECT * FROM $SHOE_TABLE WHERE id = :id")
    fun getByIdAsFlow(id: Int): Flow<ShoeWithBrandsAndColors?>

    @Query("SELECT * FROM $SHOE_TABLE")
    suspend fun listAll(): List<ShoeEntity>

    @Transaction
    @Query("SELECT * FROM $SHOE_TABLE WHERE brandId = :brandId")
    fun listByBrandID(brandId: Int): Flow<List<ShoeWithBrandsAndColors>>

    @Delete
    suspend fun delete(shoeEntity: ShoeEntity)

    @Delete
    suspend fun delete(shoeEntities: List<ShoeEntity>)
}