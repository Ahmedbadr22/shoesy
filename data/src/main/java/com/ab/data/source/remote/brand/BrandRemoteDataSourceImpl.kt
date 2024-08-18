package com.ab.data.source.remote.brand

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.BrandDto
import com.ab.data.service.BrandService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BrandRemoteDataSourceImpl(
    private val brandService: BrandService,
) : BrandRemoteDataSource {

    override suspend fun list(token: String): List<BrandDto> = safeApiCall {
        withContext(Dispatchers.IO) {
            brandService.list(token)
        }
    }
}