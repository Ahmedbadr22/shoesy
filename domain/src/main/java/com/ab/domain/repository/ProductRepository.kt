package com.ab.domain.repository

import com.ab.domain.model.data.Shoe
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getMasterDataFromRemoteToLocal(token: String)
    suspend fun listAllShoeFromRemoteToLocal(token: String)
    fun listShoesByBrandId(brandId: Int) : Flow<List<Shoe>>
    fun getByIdAsFlow(id: Int) : Flow<Shoe?>

    fun listAllFavoritesFlow() : Flow<List<Shoe>>
    fun listRandomShoeFlow() : Flow<List<Shoe>>
    fun getFavoritesCountFlow() : Flow<Int>

    suspend fun markIsFavoriteBYId(token: String, id: Int)
    suspend fun markAsNotFavoriteById(token: String, id: Int)
}