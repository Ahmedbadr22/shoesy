package com.ab.data.repository

import com.ab.data.model.mappers.toBrandList
import com.ab.data.source.remote.brand.BrandRemoteDataSource
import com.ab.domain.model.data.Brand
import com.ab.domain.repository.BrandRepository

class BrandRepositoryImpl(
    private val brandRemoteDataSource: BrandRemoteDataSource
) : BrandRepository {
    override suspend fun list(token: String): List<Brand> {
        val brandsDto = brandRemoteDataSource.list(token)
        return brandsDto.toBrandList()
    }

}