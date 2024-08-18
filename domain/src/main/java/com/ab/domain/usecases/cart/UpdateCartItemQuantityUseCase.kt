package com.ab.domain.usecases.cart

import android.util.Log
import com.ab.core.base.BaseSuspendIOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.CartItemQuantity
import com.ab.domain.repository.CartRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UpdateCartItemQuantityUseCase(
    private val cartRepository: CartRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
): BaseSuspendIOUseCase<CartItemQuantity, Flow<Resource<Unit>>> {

    override suspend fun invoke(input: CartItemQuantity): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val token = getAccessTokenUseCase()
        cartRepository.updateCartItemQuantity(token, input)
        emit(Resource.Success(Unit))
    }.catch { throwable ->
        Log.i("AHMED_BADR", "invoke: Update Quantity Error = $throwable")
        emit(Resource.Error(throwable))
    }
}