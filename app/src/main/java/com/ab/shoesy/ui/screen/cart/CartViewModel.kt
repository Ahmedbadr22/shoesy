package com.ab.shoesy.ui.screen.cart

import androidx.lifecycle.viewModelScope
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.model.data.CartItemQuantity
import com.ab.domain.usecases.cart.DeleteCartItemByIdUseCase
import com.ab.domain.usecases.cart.ListCartItemsFlowUseCase
import com.ab.domain.usecases.cart.UpdateCartItemQuantityUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CartViewModel(
    private val listCartItemsFlowUseCase: ListCartItemsFlowUseCase,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase,
    private val deleteCartItemByIdUseCase: DeleteCartItemByIdUseCase
) : BaseViewModel<CartContract.Event, CartContract.State>() {
    private var incDecCartItemJob: Job? = null

    init {
        viewModelScopeWithHandler.launch {
            listCartItemsFromLocal()
        }
    }

    private suspend fun listCartItemsFromLocal() {
        listCartItemsFlowUseCase().collectLatest { data ->
            setState { copy(items = data) }
        }
    }

    override fun setInitialState(): CartContract.State {
        return CartContract.State()
    }

    override fun handleEvents(event: CartContract.Event) {
        when(event) {
            is CartContract.Event.DecreaseCartItem -> {
                val updateQuantity = event.currentQuantity.dec()
                updateCartItemQuantity(event.cartItemId, event.shoeId, updateQuantity)
            }
            is CartContract.Event.IncreaseCartItem -> {
                val updateQuantity = event.currentQuantity.inc()
                updateCartItemQuantity(event.cartItemId, event.shoeId, updateQuantity)
            }

            is CartContract.Event.DeleteCartItem -> deleteCartItemById(event.cartItemId)
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
                    onSuccess = { },
                    onError = { }
                )
            }
        }
    }
}