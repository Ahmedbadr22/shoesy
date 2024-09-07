package com.ab.data.source.local.cart

import com.ab.data.model.entity.CartEntity
import com.ab.data.model.entity.CartItemWithShoeAndColor
import kotlinx.coroutines.flow.Flow

interface CartLocalDataSource {
    suspend fun insert(cartEntity: List<CartEntity>)
    suspend fun insert(cartEntity: CartEntity)
    fun listAsFlow() : Flow<List<CartItemWithShoeAndColor>>
    suspend fun updateQuantityById(id: Int, quantity: Int)
    suspend fun deleteById(id: Int)
    fun getCountFlow(): Flow<Int>
}