package com.ab.data.source.remote.product

import com.ab.data.model.dto.ShoeDto

interface ProductRemoteDataSource {

    suspend fun listSpecialForYou() : List<ShoeDto>
    suspend fun listShoesByBrandId(brandId: Int) : List<ShoeDto>
    suspend fun getShoeById(id: Int) : ShoeDto
}