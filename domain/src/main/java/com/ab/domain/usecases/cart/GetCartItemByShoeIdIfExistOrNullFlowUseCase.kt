package com.ab.domain.usecases.cart

import com.ab.core.base.BaseIOUseCase
import com.ab.domain.model.data.CartItem
import com.ab.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemByShoeIdIfExistOrNullFlowUseCase(
    private val cartRepository: CartRepository,
) : BaseIOUseCase<Int, Flow<CartItem?>> {

    override fun invoke(input: Int): Flow<CartItem?> = cartRepository.getByShoeIdIfExistOrNullFlow(input)
}