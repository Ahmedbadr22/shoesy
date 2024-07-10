package com.ab.data.source.remote.product

import com.ab.core.constants.API
import com.ab.data.model.dto.ShoeDto
import com.ab.data.model.request.AddFavoriteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductRemoteDataSource {

    suspend fun listSpecialForYou(token: String) : List<ShoeDto>
    suspend fun listShoesByBrandId(brandId: Int, token: String) : List<ShoeDto>
    suspend fun getShoeById(id: Int, token: String) : ShoeDto
    suspend fun listUserFavoriteShoes(@Header("Authorization") token: String) : List<ShoeDto>

    suspend fun markAsFavorite(@Header("Authorization") token: String, @Body shoeFavoriteRequest: AddFavoriteRequest)
    suspend fun unFavorite(@Header("Authorization") token: String, @Path(API.ID_PATH) shoeId: Int)
}