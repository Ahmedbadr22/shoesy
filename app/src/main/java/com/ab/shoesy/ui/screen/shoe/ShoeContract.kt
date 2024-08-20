package com.ab.shoesy.ui.screen.shoe

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.Color
import com.ab.domain.model.data.Shoe
import com.ab.shoesy.ui.screen.brand.BrandContract.Event

class ShoeContract {
    sealed class Event : ViewEvent {
        data class GetShoeById(val id: Int) : Event()
        data class MarkShoeAsFavorite(val shoeId: Int) : Event()
        data class MarkShoeAsUnFavorite(val shoeId: Int) : Event()
        data class SelectShoeColor(val color: Color): Event()
        data class SelectShoeSize(val size: Int): Event()
        data object OnIncShoeQuantity: Event()
        data object OnDecShoeQuantity: Event()
    }

    sealed class SideEffects : ViewSideEffect

    data class State(
        override val loading: Boolean = false,
        val cartItemsCount: Int = 0,
        val shoe: Shoe? = null,
        val selectedColor: Color? = null,
        val selectedSize: Int = 0,
        val quantity: Int = 0
    ) : ViewState
}
