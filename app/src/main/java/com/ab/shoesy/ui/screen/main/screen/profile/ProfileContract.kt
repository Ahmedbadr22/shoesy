package com.ab.shoesy.ui.screen.main.screen.profile

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.data.User

class ProfileContract {
    sealed class Event: ViewEvent {

    }

    sealed class SideEffects: ViewSideEffect {
    }

    data class State(
        override val loading: Boolean = false,
        val userDetail: User? = null
    ) : ViewState
}
