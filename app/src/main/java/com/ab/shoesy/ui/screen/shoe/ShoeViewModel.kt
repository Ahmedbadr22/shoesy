package com.ab.shoesy.ui.screen.shoe

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.cart.GetCartItemCountUseCase
import com.ab.domain.usecases.product.GetShoeByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShoeViewModel(
    private val getShoeByIdUseCase: GetShoeByIdUseCase,
    private val markShoeAsFavoriteByIdUseCase: MarkShoeAsFavoriteByIdUseCase,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
    private val getCartItemCountUseCase: GetCartItemCountUseCase
): BaseViewModel<ShoeContract.Event, ShoeContract.State>() {

    init {
        viewModelScope.launch {
            getCartItemsCount()
        }
    }

    override fun setInitialState(): ShoeContract.State {
        return ShoeContract.State()
    }

    override fun handleEvents(event: ShoeContract.Event) {
        when(event) {
            is ShoeContract.Event.GetShoeById -> getShoeById(event.id)
            is ShoeContract.Event.MarkShoeAsFavorite -> markAsFavoriteShoe(event.shoeId)
            is ShoeContract.Event.MarkShoeAsUnFavorite -> markAsNotFavoriteShoe(event.shoeId)
            ShoeContract.Event.OnDecShoeQuantity -> {}
            ShoeContract.Event.OnIncShoeQuantity -> {}
            is ShoeContract.Event.SelectShoeColor -> {}
            is ShoeContract.Event.SelectShoeSize -> {}
        }
    }

    private fun markAsFavoriteShoe(shoeId: Int) = viewModelScopeWithHandler.launch {
        markShoeAsFavoriteByIdUseCase(shoeId).collectLatest { resource ->
            resource.handle(
                onLoading = {},
                onSuccess = { },
                onError = { throwable ->
                    Log.i(
                        "AHMED_BADR",
                        "Error Favorite View Model markAsFavoriteShoe : $throwable "
                    )
                }
            )
        }
    }

    private fun markAsNotFavoriteShoe(shoeId: Int) = viewModelScopeWithHandler.launch {
        markShoeAsUnFavoriteByIdUseCase(shoeId).collectLatest { resource ->
            resource.handle(
                onLoading = {},
                onSuccess = { },
                onError = { throwable ->
                    Log.i(
                        "AHMED_BADR",
                        "Error Favorite View Model markAsNotFavoriteShoe : $throwable "
                    )
                }
            )
        }
    }

    private suspend fun getCartItemsCount() {
        getCartItemCountUseCase().collectLatest { count ->
            setState { copy(cartItemsCount = count) }
        }
    }

    private fun getShoeById(id: Int) {
        viewModelScopeWithHandler.launch {
            getShoeByIdUseCase(id).collectLatest { shoe ->
                setState { copy(shoe = shoe?.copy(sizes = shoe.sizes.sorted())) }
            }
        }
    }
}