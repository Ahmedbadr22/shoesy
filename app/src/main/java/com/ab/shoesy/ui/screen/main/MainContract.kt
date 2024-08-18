package com.ab.shoesy.ui.screen.main

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState

class MainContract {
    sealed class Event: ViewEvent

    sealed class SideEffects: ViewSideEffect

    data class State(
        override val loading: Boolean = false,
        val favoriteItemsCount: Int = 0,
        val cartItemsCount: Int = 0
    ) : ViewState
}
