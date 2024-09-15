package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ab.core.constants.DB.CART_TABLE
import com.ab.data.model.entity.CartEntity
import com.ab.data.model.entity.CartItemWithShoeAndColor
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartEntity: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartEntities: List<CartEntity>)

    @Transaction
    @Query("SELECT * FROM $CART_TABLE")
    fun listAllAsFlow(): Flow<List<CartItemWithShoeAndColor>>

    @Transaction
    @Query("SELECT * FROM $CART_TABLE WHERE shoeId = :shoeId")
    fun getByShoeIdIfExistOrNullFlow(shoeId: Int): Flow<CartItemWithShoeAndColor?>

    @Query("UPDATE $CART_TABLE SET quantity = :quantity  WHERE id = :id")
    suspend fun updateQuantityById(id: Int, quantity: Int)

    @Query("SELECT COUNT(*) FROM $CART_TABLE")
    fun getCountFlow() : Flow<Int>

    @Query("DELETE FROM $CART_TABLE WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Delete
    suspend fun delete(cartEntity: CartEntity)

    @Delete
    suspend fun delete(cartEntities: List<CartEntity>)
}