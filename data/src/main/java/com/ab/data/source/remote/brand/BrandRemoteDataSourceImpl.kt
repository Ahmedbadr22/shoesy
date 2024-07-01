package com.ab.data.source.remote.brand

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.BrandDto
import com.ab.data.service.BrandService

class BrandRemoteDataSourceImpl(
    private val brandService: BrandService,
) : BrandRemoteDataSource {

    override suspend fun list(token: String): List<BrandDto> = safeApiCall {
        brandService.list(token)
    }
}