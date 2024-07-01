package com.ab.data.source.remote.brand

import com.ab.data.model.dto.BrandDto

interface BrandRemoteDataSource {

    suspend fun list(token: String) : List<BrandDto>
}