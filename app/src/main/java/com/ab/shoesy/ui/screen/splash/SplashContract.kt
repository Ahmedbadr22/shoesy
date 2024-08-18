package com.ab.shoesy.ui.screen.splash

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState

class SplashContract {
    sealed class Event: ViewEvent {
    }

    sealed class SideEffects: ViewSideEffect {
        data object NavigateToLoginScreen : SideEffects()
        data object NavigateToMainScreen : SideEffects()
    }

    data class State(
        override val loading: Boolean = false,
    ) : ViewState
}
