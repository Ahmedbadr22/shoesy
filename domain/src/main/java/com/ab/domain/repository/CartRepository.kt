package com.ab.domain.repository

import com.ab.domain.model.data.CartItem
import com.ab.domain.model.data.CartItemQuantity
import kotlinx.coroutines.flow.Flow


interface CartRepository {
    fun listLocalAsFlow(): Flow<List<CartItem>>
    suspend fun listFromRemoteAndInsertToLocal(token: String)
    suspend fun updateCartItemQuantity(token: String, cartItemQuantity: CartItemQuantity)
    suspend fun deleteById(token: String, cartItemId: Int)
    fun getCountFlow(): Flow<Int>
}