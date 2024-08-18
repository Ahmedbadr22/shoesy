package com.ab.domain.usecases.cart

import com.ab.core.base.BaseOUseCase
import com.ab.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemCountUseCase(
    private val cartRepository: CartRepository,
) : BaseOUseCase<Flow<Int>> {
    override fun invoke(): Flow<Int> = cartRepository.getCountFlow()
}