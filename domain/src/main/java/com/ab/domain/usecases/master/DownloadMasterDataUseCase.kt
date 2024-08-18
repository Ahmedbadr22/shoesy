package com.ab.domain.usecases.master

import android.util.Log
import com.ab.core.base.BaseSuspendOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class DownloadMasterDataUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val productRepository: ProductRepository,
) : BaseSuspendOUseCase<Flow<Resource<Unit>>> {
    override suspend fun invoke(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        runBlocking {
            productRepository.getMasterDataFromRemoteToLocal(token)
        }
        emit(Resource.Success(Unit))
    }.catch { throwable ->
        Log.i("AHMED_BADR", "invoke: DownloadMasterDataUseCase is = $throwable")
        emit(Resource.Error(throwable))
    }
}