package com.ab.data.service

import com.ab.core.constants.API
import com.ab.data.model.dto.MasterDataDto
import com.ab.data.model.dto.ShoeDto
import com.ab.data.model.request.AddFavoriteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {
    @GET(API.GET_MASTER_DATA_ENDPOINT)
    suspend fun getMasterData(@Header("Authorization") token: String) : Response<MasterDataDto>


    @GET(API.LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT)
    suspend fun listSpecialForYou(@Header("Authorization") token: String) : Response<List<ShoeDto>>

    @GET(API.LIST_ALL_PRODUCT_ENDPOINT)
    suspend fun listAll(@Header("Authorization") token: String) : Response<List<ShoeDto>>

    @GET(API.LIST_SHOES_BY_BRAND_ID)
    suspend fun listShoesByBrandId(@Path(API.ID_PATH) brandId: Int, @Header("Authorization") token: String) : Response<List<ShoeDto>>

    @GET(API.GET_SHOE_BY_ID)
    suspend fun getShoeById(@Path(API.ID_PATH) id: Int, @Header("Authorization") token: String) : Response<ShoeDto>

    @GET(API.LIST_USER_FAVORITE_SHOES)
    suspend fun listUserFavoriteShoes(@Header("Authorization") token: String) : Response<List<ShoeDto>>

    @POST(API.POST_FAVORITE_SHOE)
    suspend fun markAsFavorite(@Header("Authorization") token: String, @Body shoeFavoriteRequest: AddFavoriteRequest) : Response<Unit>

    @DELETE(API.DELETE_FAVORITE_SHOE)
    suspend fun unFavorite(@Header("Authorization") token: String, @Path(API.ID_PATH) shoeId: Int) : Response<Unit>
}