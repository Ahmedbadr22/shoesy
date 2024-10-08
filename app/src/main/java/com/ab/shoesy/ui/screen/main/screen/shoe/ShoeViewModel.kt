package com.ab.shoesy.ui.screen.main.screen.shoe

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.model.data.CartItemQuantity
import com.ab.domain.model.data.CartOrderItem
import com.ab.domain.usecases.cart.AddCartItemUseCase
import com.ab.domain.usecases.cart.DeleteCartItemByIdUseCase
import com.ab.domain.usecases.cart.GetCartItemByShoeIdIfExistOrNullFlowUseCase
import com.ab.domain.usecases.cart.GetCartItemCountUseCase
import com.ab.domain.usecases.cart.UpdateCartItemQuantityUseCase
import com.ab.domain.usecases.product.GetShoeByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShoeViewModel(
    private val getShoeByIdUseCase: GetShoeByIdUseCase,
    private val markShoeAsFavoriteByIdUseCase: MarkShoeAsFavoriteByIdUseCase,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
    private val getCartItemCountUseCase: GetCartItemCountUseCase,
    private val addCartItemUseCase: AddCartItemUseCase,
    private val getCartItemByShoeIdIfExistOrNullFlowUseCase: GetCartItemByShoeIdIfExistOrNullFlowUseCase,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase,
    private val deleteCartItemByIdUseCase: DeleteCartItemByIdUseCase
) : BaseViewModel<ShoeContract.Event, ShoeContract.State>() {

    private var incDecCartItemJob: Job? = null

    init {
        viewModelScope.launch {
            getCartItemsCount()
        }
    }

    override fun setInitialState(): ShoeContract.State {
        return ShoeContract.State()
    }

    override fun handleEvents(event: ShoeContract.Event) {
        when (event) {
            is ShoeContract.Event.GetShoeById -> getShoeById(event.id)
            is ShoeContract.Event.MarkShoeAsFavorite -> markAsFavoriteShoe(event.shoeId)
            is ShoeContract.Event.MarkShoeAsUnFavorite -> markAsNotFavoriteShoe(event.shoeId)
            is ShoeContract.Event.OnSelectShoeQuantity -> {
                setState { copy(quantity = event.quantity) }
            }

            is ShoeContract.Event.SelectShoeColor -> {
                setState { copy(selectedColor = event.color) }
            }

            is ShoeContract.Event.SelectShoeSize -> {
                setState { copy(selectedSize = event.size) }
            }

            ShoeContract.Event.OnAddToCartClick -> onAddToCartClick()
            is ShoeContract.Event.DecreaseCartItem -> {
                val cartItem = viewState.value.cartItem ?: return

                val updateQuantity = cartItem.quantity.dec()
                updateCartItemQuantity(cartItem.id, cartItem.shoeId, updateQuantity)
            }
            is ShoeContract.Event.IncreaseCartItem -> {
                val cartItem = viewState.value.cartItem ?: return

                val updateQuantity = cartItem.quantity.inc()
                updateCartItemQuantity(cartItem.id, cartItem.shoeId, updateQuantity)
            }

            is ShoeContract.Event.DeleteCartItem -> {
                val cartItem = viewState.value.cartItem ?: return
                deleteCartItemById(cartItem.id)
            }
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
                        "Error ShoeViewModel : $throwable "
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
                        "Error ShoeViewModel : $throwable "
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
            launchCoroutine {
                getShoeByIdUseCase(id).collectLatest { shoe ->
                    setState { copy(shoe = shoe?.copy(sizes = shoe.sizes.sorted())) }
                }
            }

            launchCoroutine {
                getCartItemByShoeIdIfExistOrNullFlowUseCase(id).collectLatest { cartItem ->
                    if (cartItem != null) {
                        setState { copy(cartItem = cartItem) }
                    }
                }
            }

        }
    }

    private fun onAddToCartClick() {
        if (viewState.value.selectedSize == 0 || viewState.value.selectedColor == null || viewState.value.quantity == 0) {
            viewModelScope.launch {
                setEffect { ShoeContract.SideEffects.NotValidOrderData }
            }
        } else {

            viewState.value.shoe?.let { shoe ->
                val order = CartOrderItem(
                    shoeId = shoe.id,
                    size = viewState.value.selectedSize,
                    colorId = viewState.value.selectedColor!!.id,
                    quantity = viewState.value.quantity
                )
                launchCoroutine {
                    addCartItemUseCase(order).collectLatest { resource ->
                        resource.handle(
                            onLoading = { isLoading ->
                                setState { copy(loadingOrderProcess=isLoading) }
                            },
                            onSuccess = {
                                launch { setEffect { ShoeContract.SideEffects.SuccessCartOrderItem } }
                            },
                            onError = {
                                launch { setEffect { ShoeContract.SideEffects.FailedCartOrderItem } }
                            }
                        )
                    }
                }
            } ?: launchCoroutine { setEffect { ShoeContract.SideEffects.ShowErrorMsg("Something wont wrong..try again") } }

        }
    }


    private fun updateCartItemQuantity(cartItemId: Int, shoeId: Int, quantity: Int) {
        if (incDecCartItemJob?.isActive == true) return

        incDecCartItemJob = viewModelScopeWithHandler.launch {
            val cartItemQuantity = CartItemQuantity(cartItemId, shoeId, quantity)
            updateCartItemQuantityUseCase(cartItemQuantity).collectLatest { resource ->
                resource.handle(
                    onLoading = {},
                    onSuccess = {},
                    onError = {}
                )
            }
        }
    }

    private fun deleteCartItemById(id: Int) {
        viewModelScope.launch {
            deleteCartItemByIdUseCase(id).collectLatest { resource ->
                resource.handle(
                    onLoading = { },
                    onSuccess = {
                        setState { copy(cartItem = null) }
                    },
                    onError = { }
                )
            }
        }
    }
}