package com.ab.shoesy.ui.screen.main.screen.favorite

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.Shoe

class FavoriteContract {
    sealed class Event: ViewEvent {
        data class MarkAsUnFavoriteShoe(val shoeId: Int): Event()
    }

    sealed class SideEffects: ViewSideEffect {
    }

    data class State(
        override val loading: Boolean = false,
        val shoes: List<Shoe> = emptyList()
    ) : ViewState
}
