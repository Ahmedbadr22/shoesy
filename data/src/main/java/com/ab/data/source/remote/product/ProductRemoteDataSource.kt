package com.ab.data.source.remote.product

import com.ab.data.model.dto.ShoeDto

interface ProductRemoteDataSource {

    suspend fun listSpecialForYou() : List<ShoeDto>
}