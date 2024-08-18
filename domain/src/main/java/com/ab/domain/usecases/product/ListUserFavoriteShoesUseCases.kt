package com.ab.domain.usecases.product

import com.ab.core.base.BaseOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListUserFavoriteShoesUseCases(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val productRepository: ProductRepository
) : BaseOUseCase<Flow<Resource<List<Shoe>>>> {
    override fun invoke(): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        // val shoes = productRepository.listUserFavoriteShoes(token)
        emit(Resource.Success(emptyList<Shoe>()))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}