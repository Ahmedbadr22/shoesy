package com.ab.domain.usecases.product

import com.ab.core.base.BaseIOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetShoeByIdUseCase(
    private val productRepository: ProductRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : BaseIOUseCase<Int, Flow<Resource<Shoe>>> {
    override fun invoke(input: Int): Flow<Resource<Shoe>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        val shoe = productRepository.getShoeById(input, token)
        emit(Resource.Success(shoe))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}