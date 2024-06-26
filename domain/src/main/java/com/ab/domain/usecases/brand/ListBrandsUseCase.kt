package com.ab.domain.usecases.brand

import com.ab.core.base.BaseSuspendOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Brand
import com.ab.domain.repository.BrandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListBrandsUseCase(
    private val brandRepository: BrandRepository
) : BaseSuspendOUseCase<Flow<Resource<List<Brand>>>> {
    override suspend fun invoke(): Flow<Resource<List<Brand>>> = flow {
        emit(Resource.Loading)
        val brands = brandRepository.list()
        emit(Resource.Success(brands))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}