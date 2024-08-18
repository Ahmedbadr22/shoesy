package com.ab.domain.usecases.product

import com.ab.core.base.BaseOUseCase
import com.ab.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteShoeCountUseCase(
    private val productRepository: ProductRepository,
) : BaseOUseCase<Flow<Int>> {
    override fun invoke(): Flow<Int> = productRepository.getFavoritesCountFlow()
}