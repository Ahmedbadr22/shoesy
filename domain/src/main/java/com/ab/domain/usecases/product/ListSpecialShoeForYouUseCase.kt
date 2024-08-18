package com.ab.domain.usecases.product

import com.ab.core.base.BaseOUseCase
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ListSpecialShoeForYouUseCase(
    private val productRepository: ProductRepository,
) : BaseOUseCase<Flow<List<Shoe>>> {
    override fun invoke(): Flow<List<Shoe>> = productRepository.listRandomShoeFlow()
}