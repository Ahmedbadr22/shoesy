package com.ab.data.source.remote.product

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.MasterDataDto
import com.ab.data.model.dto.ShoeDto
import com.ab.data.model.request.AddFavoriteRequest
import com.ab.data.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRemoteDataSourceImpl(
    private val productService: ProductService
) : ProductRemoteDataSource {
    override suspend fun getMasterData(token: String): MasterDataDto = withContext(Dispatchers.IO) {
        safeApiCall { productService.getMasterData(token) }
    }


    override suspend fun listSpecialForYou(token: String): List<ShoeDto> = safeApiCall {
        withContext(Dispatchers.IO) {
            productService.listSpecialForYou(token)
        }
    }

    override suspend fun listAll(token: String): List<ShoeDto> = safeApiCall {
        withContext(Dispatchers.IO) {
            productService.listAll(token)
        }
    }

    override suspend fun listShoesByBrandId(brandId: Int, token: String): List<ShoeDto> =
        safeApiCall {
            withContext(Dispatchers.IO) {
                productService.listShoesByBrandId(brandId, token)
            }
        }

    override suspend fun getShoeById(id: Int, token: String): ShoeDto = safeApiCall {
        withContext(Dispatchers.IO) {
            productService.getShoeById(id, token)
        }
    }

    override suspend fun listUserFavoriteShoes(token: String): List<ShoeDto> = safeApiCall {
        withContext(Dispatchers.IO) {
            productService.listUserFavoriteShoes(token)
        }
    }

    override suspend fun markAsFavorite(token: String, shoeFavoriteRequest: AddFavoriteRequest) {
        safeApiCall {
            withContext(Dispatchers.IO) {
                productService.markAsFavorite(token, shoeFavoriteRequest)
            }
        }
    }

    override suspend fun markAsNotFavorite(token: String, shoeId: Int) {
        safeApiCall {
            withContext(Dispatchers.IO) {
                productService.unFavorite(token, shoeId)
            }
        }
    }
}