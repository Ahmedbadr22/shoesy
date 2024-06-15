package com.ab.shoesy.ui.screen.login

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.auth.LoginUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginContract.Event, LoginContract.State>() {
    override fun setInitialState(): LoginContract.State {
        return LoginContract.State()
    }

    override fun handleEvents(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnEmailChange -> setState { copy(email = event.value) }
            is LoginContract.Event.OnPasswordChange -> setState { copy(password = event.value) }
            LoginContract.Event.OnLogin -> login()
            is LoginContract.Event.OnShowPasswordStateChange -> setState { copy(showPassword = event.show) }
        }
    }

    private fun login() {
        viewModelScopeWithHandler.launch {
            val loginForm = viewState.value.getLoginForm()

            loginUseCase(loginForm).collectLatest { resource ->
                resource.handle(
                    onLoading = { isLoading -> setState { copy(loading = isLoading) }},
                    onSuccess = { data -> Log.i("AHMED_BADR", "login: $data") },
                    onError = { error -> showError(error.message.orEmpty()) }
                )
            }
        }
    }
}