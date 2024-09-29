package com.ab.shoesy.ui.screen.main.screen.cart

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.CartItem
import com.ab.domain.model.data.getTotalPrice

class CartContract {
    sealed class Event : ViewEvent {
        data class IncreaseCartItem(
            val cartItemId: Int,
            val shoeId: Int,
            val currentQuantity: Int
        ) : Event()

        data class DecreaseCartItem(
            val cartItemId: Int,
            val shoeId: Int,
            val currentQuantity: Int
        ) : Event()

        data class DeleteCartItem(val cartItemId: Int) : Event()
    }

    sealed class SideEffects : ViewSideEffect {

    }

    data class State(
        override val loading: Boolean = false,
        val items: List<CartItem> = emptyList(),
    ) : ViewState {
        fun calculateTotal(): Float = items.sumOf { item -> item.getTotalPrice().toDouble() }.toFloat()
        fun calculateTaxValue(): Float = calculateTotal() * 0.14f
        fun calculateGrandTotal(): Float = calculateTotal() + calculateTaxValue()
    }
}
