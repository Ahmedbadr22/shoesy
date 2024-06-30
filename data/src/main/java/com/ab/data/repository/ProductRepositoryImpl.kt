package com.ab.data.repository

import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toDomainList
import com.ab.data.source.remote.product.ProductRemoteDataSource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val stockRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun listSpecialForYou(): List<Shoe> {
        val shoes = stockRemoteDataSource.listSpecialForYou()
        return shoes.toDomainList()
    }

    override suspend fun listShoesByBrandId(brandId: Int): List<Shoe> {
        val shoes = stockRemoteDataSource.listShoesByBrandId(brandId)
        return shoes.toDomainList()
    }

    override suspend fun getShoeById(id: Int): Shoe {
        val shoes = stockRemoteDataSource.getShoeById(id)
        return shoes.toDomain()
    }
}