package com.ab.domain.usecases.cart


import android.util.Log
import com.ab.core.base.BaseSuspendIOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.repository.CartRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DeleteCartItemByIdUseCase(
    private val cartRepository: CartRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
): BaseSuspendIOUseCase<Int, Flow<Resource<Unit>>> {

    override suspend fun invoke(input: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        cartRepository.deleteById(token, input)
        emit(Resource.Success(Unit))
    }.catch { throwable ->
        Log.i("AHMED_BADR", "invoke: Delete Quantity Error = $throwable")
        emit(Resource.Error(throwable))
    }
}