package com.ab.data.service

import com.ab.core.constants.EndPoint
import com.ab.data.model.dto.BrandDto
import retrofit2.Response
import retrofit2.http.GET

interface BrandService {
    @GET(EndPoint.LIST_BRANDS_ENDPOINT)
    suspend fun list() : Response<List<BrandDto>>
}