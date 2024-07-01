package com.ab.domain.usecases.brand

import com.ab.core.base.BaseSuspendOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Brand
import com.ab.domain.repository.BrandRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListBrandsUseCase(
    private val brandRepository: BrandRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : BaseSuspendOUseCase<Flow<Resource<List<Brand>>>> {
    override suspend fun invoke(): Flow<Resource<List<Brand>>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        val brands = brandRepository.list(token)
        emit(Resource.Success(brands))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}