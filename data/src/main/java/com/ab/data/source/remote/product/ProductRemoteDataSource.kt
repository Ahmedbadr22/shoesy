package com.ab.data.source.remote.product

import com.ab.data.model.dto.ShoeDto

interface ProductRemoteDataSource {

    suspend fun listSpecialForYou(token: String) : List<ShoeDto>
    suspend fun listShoesByBrandId(brandId: Int, token: String) : List<ShoeDto>
    suspend fun getShoeById(id: Int, token: String) : ShoeDto
}