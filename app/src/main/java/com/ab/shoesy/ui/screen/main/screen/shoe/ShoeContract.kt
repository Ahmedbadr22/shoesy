package com.ab.shoesy.ui.screen.main.screen.shoe

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.CartItem
import com.ab.domain.model.data.Color
import com.ab.domain.model.data.Shoe
import com.ab.shoesy.ui.screen.main.screen.brand.BrandContract.Event

class ShoeContract {
    sealed class Event : ViewEvent {
        data class GetShoeById(val id: Int) : Event()
        data class MarkShoeAsFavorite(val shoeId: Int) : Event()
        data class MarkShoeAsUnFavorite(val shoeId: Int) : Event()
        data object OnAddToCartClick: Event()
        data class SelectShoeColor(val color: Color): Event()
        data class SelectShoeSize(val size: Int): Event()
        data class OnSelectShoeQuantity(val quantity: Int): Event()
        data object IncreaseCartItem: Event()
        data object DecreaseCartItem: Event()
        data object DeleteCartItem: Event()

    }

    sealed class SideEffects : ViewSideEffect {
        data object NotValidOrderData: SideEffects()
        data object SuccessCartOrderItem: SideEffects()
        data object FailedCartOrderItem: SideEffects()
        data class ShowErrorMsg(val msg: String): SideEffects()
    }

    data class State(
        override val loading: Boolean = false,
        val loadingOrderProcess: Boolean = false,
        val cartItemsCount: Int = 0,
        val cartItem: CartItem? = null,
        val shoe: Shoe? = null,
        val selectedColor: Color? = null,
        val selectedSize: Int = 0,
        val quantity: Int = 0
    ) : ViewState {
        fun calculateTotalCost() : Float = shoe?.price?.times(quantity) ?: 0.0f
    }
}
