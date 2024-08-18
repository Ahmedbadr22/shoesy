package com.ab.shoesy.ui.screen.brand

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.Brand
import com.ab.domain.model.data.Shoe

class BrandContract {
    sealed class Event: ViewEvent {
        data class ListShoesByBrandId(val brandId: Int) : Event()
        data object ListBrands : Event()
        data class MarkShoeAsFavorite(val shoeId: Int) : Event()
        data class MarkShoeAsUnFavorite(val shoeId: Int) : Event()
    }

    sealed class SideEffects: ViewSideEffect

    data class State(
        override val loading: Boolean = false,
        val shoes: List<Shoe> = emptyList(),
        val brands: List<Brand> = emptyList()
    ) : ViewState
}
