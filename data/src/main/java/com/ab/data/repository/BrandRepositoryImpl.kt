package com.ab.data.repository

import com.ab.data.model.mappers.toDomainList
import com.ab.data.model.mappers.toEntities
import com.ab.data.source.local.brand.BrandLocalDataSource
import com.ab.data.source.remote.brand.BrandRemoteDataSource
import com.ab.domain.model.data.Brand
import com.ab.domain.repository.BrandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BrandRepositoryImpl(
    private val brandRemoteDataSource: BrandRemoteDataSource,
    private val brandLocalDataSource: BrandLocalDataSource
) : BrandRepository {

    override val brandsFlow: Flow<List<Brand>>
        get() = brandLocalDataSource
            .listAllAsFlow()
            .map{ brandEntities -> brandEntities.toDomainList() }

    override suspend fun listFromRemoteAndInsertToLocal(token: String) {
        val brandDtoList = brandRemoteDataSource.list(token)
        val brandEntities = brandDtoList.toEntities()
        brandLocalDataSource.insert(brandEntities)
    }
}