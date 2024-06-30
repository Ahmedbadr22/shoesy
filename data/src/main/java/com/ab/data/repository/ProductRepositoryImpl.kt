package com.ab.data.repository

import com.ab.data.model.mappers.toDomainList
import com.ab.data.source.remote.product.ProductRemoteDataSource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val stockRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun listSpecialForYou(): List<Shoe> {
        val stockItems = stockRemoteDataSource.listSpecialForYou()
        return stockItems.toDomainList()
    }
}