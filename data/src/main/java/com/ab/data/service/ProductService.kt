package com.ab.data.service

import com.ab.core.constants.EndPoint
import com.ab.data.model.dto.ShoeDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET(EndPoint.LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT)
    suspend fun listSpecialForYou() : Response<List<ShoeDto>>
}