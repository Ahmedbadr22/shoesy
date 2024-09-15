package com.ab.shoesy.ui.screen.main.screen.cart

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.CartItem

class CartContract {
    sealed class Event: ViewEvent {
        data class IncreaseCartItem(val cartItemId: Int, val shoeId: Int, val currentQuantity: Int): Event()
        data class DecreaseCartItem(val cartItemId: Int, val shoeId: Int, val currentQuantity: Int): Event()
        data class DeleteCartItem(val cartItemId: Int): Event()
    }

    sealed class SideEffects: ViewSideEffect {

    }

    data class State(
        override val loading: Boolean = false,
        val items: List<CartItem> = emptyList()
    ) : ViewState
}
