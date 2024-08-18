package com.ab.domain.usecases.cart

import com.ab.core.base.BaseOUseCase
import com.ab.domain.model.data.CartItem
import com.ab.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class ListCartItemsFlowUseCase(
    private val cartRepository: CartRepository
): BaseOUseCase<Flow<List<CartItem>>> {
    override fun invoke(): Flow<List<CartItem>> = cartRepository.listLocalAsFlow()
}