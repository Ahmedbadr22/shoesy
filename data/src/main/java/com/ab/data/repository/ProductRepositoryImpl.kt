package com.ab.data.repository

import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toDomainList
import com.ab.data.model.request.AddFavoriteRequest
import com.ab.data.source.remote.product.ProductRemoteDataSource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val stockRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun listSpecialForYou(token: String): List<Shoe> {
        val shoes = stockRemoteDataSource.listSpecialForYou(token)
        return shoes.toDomainList()
    }

    override suspend fun listShoesByBrandId(brandId: Int, token: String): List<Shoe> {
        val shoes = stockRemoteDataSource.listShoesByBrandId(brandId, token)
        return shoes.toDomainList()
    }

    override suspend fun getShoeById(id: Int, token: String): Shoe {
        val shoes = stockRemoteDataSource.getShoeById(id, token)
        return shoes.toDomain()
    }

    override suspend fun listUserFavoriteShoes(token: String): List<Shoe> {
        val shoes = stockRemoteDataSource.listUserFavoriteShoes(token)
        return shoes.toDomainList()
    }

    override suspend fun markAsFavorite(token: String, shoeId: Int) {
        val favoriteRequest = AddFavoriteRequest(shoeId)
        stockRemoteDataSource.markAsFavorite(token, favoriteRequest)
    }

    override suspend fun unFavorite(token: String, shoeId: Int) {
        stockRemoteDataSource.unFavorite(token, shoeId)
    }
}