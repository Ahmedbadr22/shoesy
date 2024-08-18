package com.ab.domain.usecases.product

import com.ab.core.base.BaseIOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListShoesByBrandIdUseCase(
    private val productRepository: ProductRepository,
) : BaseIOUseCase<Int, Flow<List<Shoe>>> {
    override fun invoke(input: Int): Flow<List<Shoe>> = productRepository.listShoesByBrandId(input)
}