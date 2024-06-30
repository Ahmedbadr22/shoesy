package com.ab.domain.usecases.product

import com.ab.core.base.BaseIOUseCase
import com.ab.core.base.BaseOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListShoesByBrandIdUseCase(
    private val productRepository: ProductRepository
) : BaseIOUseCase<Int, Flow<Resource<List<Shoe>>>> {
    override fun invoke(input: Int): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading)
        val shoes = productRepository.listShoesByBrandId(input)
        emit(Resource.Success(shoes))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}