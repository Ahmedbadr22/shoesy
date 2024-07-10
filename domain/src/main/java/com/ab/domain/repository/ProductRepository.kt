package com.ab.domain.repository

import com.ab.domain.model.data.Shoe

interface ProductRepository {
    suspend fun listSpecialForYou(token: String) : List<Shoe>
    suspend fun listShoesByBrandId(brandId: Int, token: String) : List<Shoe>
    suspend fun getShoeById(id: Int, token: String) : Shoe
    suspend fun listUserFavoriteShoes(token: String) : List<Shoe>

    suspend fun markAsFavorite(token: String, shoeId: Int)
    suspend fun unFavorite(token: String, shoeId: Int)
}