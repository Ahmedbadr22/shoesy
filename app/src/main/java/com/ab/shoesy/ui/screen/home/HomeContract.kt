package com.ab.shoesy.ui.screen.home

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.Brand
import com.ab.domain.model.data.Shoe

class HomeContract {
    sealed class Event: ViewEvent {
    }

    sealed class SideEffects: ViewSideEffect {
    }

    data class State(
        override val loading: Boolean = false,
        val brands: List<Brand> = emptyList(),
        val specialForYouShoes: List<Shoe> = emptyList()
    ) : ViewState
}
