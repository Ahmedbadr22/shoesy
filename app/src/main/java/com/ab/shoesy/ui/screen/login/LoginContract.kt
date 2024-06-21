package com.ab.shoesy.ui.screen.login

import com.ab.core.base.ViewEvent
import com.ab.core.base.ViewSideEffect
import com.ab.core.base.ViewState
import com.ab.domain.model.form.LoginForm

class LoginContract {
    sealed class Event: ViewEvent {
        data object OnLogin: Event()
        data class OnEmailChange(val value: String): Event()
        data class OnPasswordChange(val value: String): Event()
        data class OnShowPasswordStateChange(val show: Boolean): Event()
    }

    sealed class SideEffects: ViewSideEffect {
        data class ShowErrorDialog(val resId: Int) : SideEffects()
        data object NavigateToMain : SideEffects()
    }

    data class State(
        override val loading: Boolean = false,
        val email: String = "",
        val emailErrorResIdMessage: Int = 0,
        val password: String = "",
        val passwordErrorResIdMessage: Int = 0,
        val showPassword: Boolean = false,
    ) : ViewState
}

fun LoginContract.State.getLoginForm(): LoginForm = LoginForm(email, password)