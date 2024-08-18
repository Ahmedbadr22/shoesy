package com.ab.data.source.remote.cart

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.CartDto
import com.ab.data.model.request.CartItemQuantityRequest
import com.ab.data.model.request.CartItemRequest
import com.ab.data.service.CartService

class CartRemoteDataSourceImpl(
    private val cartService: CartService
): CartRemoteDataSource {

    override suspend fun addItem(token: String, cartItemRequest: CartItemRequest) {
        safeApiCall {
            cartService.postItem(token, cartItemRequest)
        }
    }

    override suspend fun listItems(token: String): List<CartDto> = safeApiCall {
        cartService.list(token)
    }

    override suspend fun updateItemQuantity(
        cartItemId: Int,
        token: String,
        cartItemQuantityRequest: CartItemQuantityRequest
    ) {
        safeApiCall {
            cartService.updateQuantity(token, cartItemId, cartItemQuantityRequest)
        }
    }

    override suspend fun deleteItem(cartItemId: Int, token: String) {
        safeApiCall {
            cartService.delete(token, cartItemId)
        }
    }
}