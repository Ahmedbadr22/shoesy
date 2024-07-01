package com.ab.data.service

import com.ab.core.constants.API
import com.ab.data.model.dto.BrandDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BrandService {
    @GET(API.LIST_BRANDS_ENDPOINT)
    suspend fun list(@Header("Authorization") token: String) : Response<List<BrandDto>>
}