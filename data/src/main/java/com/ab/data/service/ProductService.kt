package com.ab.data.service

import com.ab.core.constants.API
import com.ab.data.model.dto.ShoeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProductService {
    @GET(API.LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT)
    suspend fun listSpecialForYou(@Header("Authorization") token: String) : Response<List<ShoeDto>>


    @GET(API.LIST_SHOES_BY_BRAND_ID)
    suspend fun listShoesByBrandId(@Path(API.ID_PATH) brandId: Int, @Header("Authorization") token: String) : Response<List<ShoeDto>>

    @GET(API.GET_SHOE_BY_ID)
    suspend fun getShoeById(@Path(API.ID_PATH) id: Int, @Header("Authorization") token: String) : Response<ShoeDto>
}