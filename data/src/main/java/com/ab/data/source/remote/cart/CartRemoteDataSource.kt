package com.ab.data.source.remote.cart

import com.ab.data.model.dto.CartDto
import com.ab.data.model.dto.ColorDto
import com.ab.data.model.request.CartItemQuantityRequest
import com.ab.data.model.request.CartItemRequest

interface CartRemoteDataSource {
    suspend fun addItem(token: String, cartItemRequest: CartItemRequest): CartDto
    suspend fun listItems(token: String) : List<CartDto>
    suspend fun updateItemQuantity(cartItemId: Int, token: String, cartItemQuantityRequest: CartItemQuantityRequest)
    suspend fun deleteItem(cartItemId: Int, token: String)
}