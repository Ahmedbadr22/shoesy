package com.ab.data.service

import com.ab.core.constants.API
import com.ab.data.model.dto.CartDto
import com.ab.data.model.request.CartItemQuantityRequest
import com.ab.data.model.request.CartItemRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CartService {
    @POST(API.POST_CART_ITEM_ENDPOINT)
    suspend fun postItem(@Header("Authorization") token: String, @Body cartItemRequest: CartItemRequest): Response<Void>

    @GET(API.LIST_CART_ITEMS_ENDPOINT)
    suspend fun list(@Header("Authorization") token: String) : Response<List<CartDto>>

    @PUT(API.UPDATE_CART_ITEM_QUANTITY_ENDPOINT)
    suspend fun updateQuantity(@Header("Authorization") token: String, @Path(API.ID_PATH) cartItemId: Int, @Body cartItemQuantityRequest: CartItemQuantityRequest): Response<Void?>

    @DELETE(API.DELETE_CART_ITEM_ENDPOINT)
    suspend fun delete(@Header("Authorization") token: String, @Path(API.ID_PATH) cartItemId: Int): Response<Void?>
}