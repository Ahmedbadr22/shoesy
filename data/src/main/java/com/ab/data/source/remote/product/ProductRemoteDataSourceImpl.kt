package com.ab.data.source.remote.product

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.ShoeDto
import com.ab.data.service.ProductService

class ProductRemoteDataSourceImpl(
    private val productService: ProductService
) : ProductRemoteDataSource {

    override suspend fun listSpecialForYou(token: String): List<ShoeDto> = safeApiCall {
        productService.listSpecialForYou(token)
    }

    override suspend fun listShoesByBrandId(brandId: Int, token: String): List<ShoeDto> = safeApiCall {
        productService.listShoesByBrandId(brandId, token)
    }

    override suspend fun getShoeById(id: Int, token: String): ShoeDto = safeApiCall {
        productService.getShoeById(id, token)
    }
}