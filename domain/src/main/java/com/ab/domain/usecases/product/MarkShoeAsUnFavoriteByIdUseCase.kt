package com.ab.domain.usecases.product

import com.ab.core.base.BaseIOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MarkShoeAsUnFavoriteByIdUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val productRepository: ProductRepository
) : BaseIOUseCase<Int, Flow<Resource<Unit>>> {
    override fun invoke(input: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        productRepository.unFavorite(token, input)
        emit(Resource.Success(Unit))
    }.catch { cause: Throwable ->
        emit(Resource.Error(cause))
    }
}