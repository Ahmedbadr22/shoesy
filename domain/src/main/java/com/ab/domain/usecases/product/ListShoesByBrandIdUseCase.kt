package com.ab.domain.usecases.product

import com.ab.core.base.BaseIOUseCase
import com.ab.core.base.BaseOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListShoesByBrandIdUseCase(
    private val productRepository: ProductRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : BaseIOUseCase<Int, Flow<Resource<List<Shoe>>>> {
    override fun invoke(input: Int): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        val shoes = productRepository.listShoesByBrandId(input, token)
        emit(Resource.Success(shoes))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}