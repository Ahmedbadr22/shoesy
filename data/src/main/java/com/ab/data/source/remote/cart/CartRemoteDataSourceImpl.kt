package com.ab.data.source.remote.cart

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.CartDto
import com.ab.data.model.request.CartItemQuantityRequest
import com.ab.data.model.request.CartItemRequest
import com.ab.data.service.CartService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRemoteDataSourceImpl(
    private val cartService: CartService
): CartRemoteDataSource {

    override suspend fun addItem(token: String, cartItemRequest: CartItemRequest) : CartDto = withContext(Dispatchers.IO) {
        safeApiCall {
            cartService.postItem(token, cartItemRequest)
        }
    }

    override suspend fun listItems(token: String): List<CartDto> = withContext(Dispatchers.IO) {
        safeApiCall {
            cartService.list(token)
        }
    }

    override suspend fun updateItemQuantity(
        cartItemId: Int,
        token: String,
        cartItemQuantityRequest: CartItemQuantityRequest
    ) {
        withContext(Dispatchers.IO) {
            safeApiCall {
                cartService.updateQuantity(token, cartItemId, cartItemQuantityRequest)
            }
        }
    }

    override suspend fun deleteItem(cartItemId: Int, token: String) {
        withContext(Dispatchers.IO) {
            safeApiCall {
                cartService.delete(token, cartItemId)
            }
        }
    }
}