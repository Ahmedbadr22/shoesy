package com.ab.data.repository

import com.ab.data.model.dto.BrandDto
import com.ab.data.model.mappers.toBrandList
import com.ab.data.source.remote.brand.BrandRemoteDataSource
import com.ab.domain.model.data.Brand
import com.ab.domain.repository.BrandRepository

class BrandRepositoryImpl(
    private val brandRemoteDataSource: BrandRemoteDataSource
) : BrandRepository {
    private val _cashedBrands: MutableMap<Int, BrandDto> = mutableMapOf()

    override suspend fun list(token: String): List<Brand> {
        val brands = synchronized(this) { _cashedBrands }
        if (brands.isNotEmpty()) {
            return _cashedBrands.values.toList().toBrandList()
        } else {
            val brandsDtoList = brandRemoteDataSource.list(token)
            if (brandsDtoList.isNotEmpty()) {
                synchronized(this) {
                    brandsDtoList.forEach { brandDto ->
                        _cashedBrands[brandDto.id] = brandDto
                    }
                }
            }
            return brandsDtoList.toBrandList()
        }
    }
}