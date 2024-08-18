package com.ab.domain.usecases.master

import android.util.Log
import com.ab.core.base.BaseSuspendOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.repository.BrandRepository
import com.ab.domain.repository.CartRepository
import com.ab.domain.repository.ColorRepository
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class DownloadMasterDataUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val brandRepository: BrandRepository,
    private val productRepository: ProductRepository,
    private val colorRepository: ColorRepository,
    private val cartRepository: CartRepository
) : BaseSuspendOUseCase<Flow<Resource<Unit>>> {
    override suspend fun invoke(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        runBlocking {
            brandRepository.listFromRemoteAndInsertToLocal(token)
            productRepository.listAllShoeFromRemoteToLocal(token)
            colorRepository.listFromRemoteAndInsertToLocal(token)
            cartRepository.listFromRemoteAndInsertToLocal(token)
        }
        emit(Resource.Success(Unit))
    }.catch { throwable ->
        Log.i("AHMED_BADR", "invoke: DownloadMasterDataUseCase is = $throwable")
        emit(Resource.Error(throwable))
    }
}