package com.ab.shoesy.ui.screen.login

import com.ab.core.base.BaseViewModel
import com.ab.core.utils.NetworkRequestException
import com.ab.core.utils.ValidationResource
import com.ab.core.utils.handle
import com.ab.domain.usecases.auth.LoginUseCase
import com.ab.domain.usecases.master.DownloadMasterDataUseCase
import com.ab.domain.usecases.utils.EmailValidationUseCase
import com.ab.domain.usecases.utils.PasswordValidationUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    private val downloadMasterDataUseCase: DownloadMasterDataUseCase
): BaseViewModel<LoginContract.Event, LoginContract.State>() {
    override fun setInitialState(): LoginContract.State {
        return LoginContract.State()
    }

    override fun handleEvents(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnEmailChange -> setState { copy(email = event.value, emailErrorResIdMessage = 0) }
            is LoginContract.Event.OnPasswordChange -> setState { copy(password = event.value, passwordErrorResIdMessage = 0) }
            LoginContract.Event.OnLogin -> login()
            is LoginContract.Event.OnShowPasswordStateChange -> setState { copy(showPassword = event.show) }
        }
    }

    private fun login() {
        viewModelScopeWithHandler.launch {
            var isValidEmail = false
            var isValidPassword = false
            val loginForm = viewState.value.getLoginForm()

            emailValidationUseCase(loginForm.email).collectLatest { validationResource: ValidationResource ->
                validationResource.handle(
                    onValid = {
                        isValidEmail = true
                    },
                    onNotValid = { stringResourceIdMessage ->
                        setState { copy(emailErrorResIdMessage = stringResourceIdMessage) }
                    }
                )
            }

            passwordValidationUseCase(loginForm.password).collectLatest { validationResource: ValidationResource ->
                validationResource.handle(
                    onValid = {
                        isValidPassword = true
                    },
                    onNotValid = { stringResourceIdMessage ->
                        setState { copy(passwordErrorResIdMessage = stringResourceIdMessage) }
                    }
                )
            }


            if (isValidEmail && isValidPassword) {
                loginUseCase(loginForm).collectLatest { resource ->
                    resource.handle(
                        onLoading = { isLoading -> setState { copy(loading = isLoading) }},
                        onSuccess = { _ ->
                            launch {
                                runBlocking {
                                    downloadMasterDataUseCase().collect()
                                    setEffect { LoginContract.SideEffects.NavigateToMain }
                                }
                            }
                        },
                        onError = { error ->
                            launch {
                                setEffect {
                                    LoginContract.SideEffects.ShowErrorDialog((error as NetworkRequestException).messageResId)
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}