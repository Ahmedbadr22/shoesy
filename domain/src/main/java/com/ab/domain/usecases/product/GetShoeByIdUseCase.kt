package com.ab.domain.usecases.product

import com.ab.core.base.BaseIOUseCase
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetShoeByIdUseCase(
    private val productRepository: ProductRepository,
) : BaseIOUseCase<Int, Flow<Shoe?>> {
    override fun invoke(input: Int): Flow<Shoe?> = productRepository.getByIdAsFlow(input)
}