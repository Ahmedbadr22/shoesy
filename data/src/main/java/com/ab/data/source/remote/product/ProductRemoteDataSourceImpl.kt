package com.ab.data.source.remote.product

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.ShoeDto
import com.ab.data.service.ProductService

class ProductRemoteDataSourceImpl(
    private val productService: ProductService
) : ProductRemoteDataSource {

    override suspend fun listSpecialForYou(): List<ShoeDto> = safeApiCall {
        productService.listSpecialForYou()
    }
}