package com.ab.data.repository

import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toEntities
import com.ab.data.model.mappers.toEntity
import com.ab.data.model.mappers.toRequest
import com.ab.data.source.local.cart.CartLocalDataSource
import com.ab.data.source.remote.cart.CartRemoteDataSource
import com.ab.domain.model.data.CartItem
import com.ab.domain.model.data.CartItemQuantity
import com.ab.domain.model.data.CartOrderItem
import com.ab.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CartRepositoryImpl(
    private val cartRemoteDataSource: CartRemoteDataSource,
    private val cartLocalDataSource: CartLocalDataSource
): CartRepository {
    override suspend fun createCartItem(token: String, cartOrderItem: CartOrderItem) {
        val cartItemRequest = cartOrderItem.toRequest()
        val cartItemDto = cartRemoteDataSource.addItem(token, cartItemRequest)
        val cartEntity = cartOrderItem.toEntity(cartItemDto.id)
        cartLocalDataSource.insert(cartEntity)
    }

    override fun listLocalAsFlow(): Flow<List<CartItem>> = cartLocalDataSource
        .listAsFlow()
        .map { cartItems -> cartItems.toDomain() }

    override suspend fun listFromRemoteAndInsertToLocal(token: String) {
        val cartDtoItems = cartRemoteDataSource.listItems(token)
        val cartEntities = cartDtoItems.toEntities()
        cartLocalDataSource.insert(cartEntities)
    }

    override suspend fun updateCartItemQuantity(token: String, cartItemQuantity: CartItemQuantity) {
        val cartItemQuantityRequest = cartItemQuantity.toRequest()
        cartRemoteDataSource.updateItemQuantity(cartItemQuantity.id, token, cartItemQuantityRequest)
        cartLocalDataSource.updateQuantityById(cartItemQuantity.id, cartItemQuantityRequest.quantity)
    }

    override suspend fun deleteById(token: String, cartItemId: Int) {
        cartRemoteDataSource.deleteItem(cartItemId, token)
        cartLocalDataSource.deleteById(cartItemId)
    }

    override fun getCountFlow(): Flow<Int> = cartLocalDataSource.getCountFlow()

}