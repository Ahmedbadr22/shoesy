package com.ab.domain.usecases.product

import com.ab.core.base.BaseOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListShoesFromRemoteToLocalUseCase(
    private val productRepository: ProductRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : BaseOUseCase<Flow<Resource<Unit>>> {
    override fun invoke(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        productRepository.listAllShoeFromRemoteToLocal(token)
        emit(Resource.Success(Unit))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}