package com.ab.data.source.remote.product

import com.ab.data.model.dto.MasterDataDto
import com.ab.data.model.dto.ShoeDto
import com.ab.data.model.request.AddFavoriteRequest

interface ProductRemoteDataSource {

    suspend fun getMasterData(token: String) : MasterDataDto
    suspend fun listSpecialForYou(token: String) : List<ShoeDto>
    suspend fun listAll(token: String) : List<ShoeDto>
    suspend fun listShoesByBrandId(brandId: Int, token: String) : List<ShoeDto>
    suspend fun getShoeById(id: Int, token: String) : ShoeDto
    suspend fun listUserFavoriteShoes(token: String) : List<ShoeDto>

    suspend fun markAsFavorite(token: String,  shoeFavoriteRequest: AddFavoriteRequest)
    suspend fun markAsNotFavorite(token: String, shoeId: Int)
}