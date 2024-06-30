package com.ab.shoesy.ui.screen.shoe

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.Brand
import com.ab.domain.model.data.Shoe

class ShoeContract {
    sealed class Event: ViewEvent {
        data class GetShoeById(val id: Int) : Event()
    }

    sealed class SideEffects: ViewSideEffect {
    }

    data class State(
        override val loading: Boolean = false,
        val shoe: Shoe? = null
    ) : ViewState
}
