package com.ab.shoesy.ui.screen.splash

import com.ab.core.base.BaseViewModel
import com.ab.domain.usecases.auth.IsAuthenticatedUserUseCase
import com.ab.domain.usecases.master.DownloadMasterDataUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(
    private val isAuthenticatedUserUseCase: IsAuthenticatedUserUseCase,
    private val downloadMasterDataUseCase: DownloadMasterDataUseCase
) : BaseViewModel<SplashContract.Event, SplashContract.State>() {

    init {
        viewModelScopeWithHandler.launch {
            launch {
                checkIsAuthenticated()
            }

            launch {
                downloadMasterDataUseCase().collect()
            }
        }
    }

    private suspend fun checkIsAuthenticated() {
        val isAuthenticated = isAuthenticatedUserUseCase()

        if (isAuthenticated) setEffect { SplashContract.SideEffects.NavigateToMainScreen }
        else setEffect { SplashContract.SideEffects.NavigateToLoginScreen }
    }

    override fun setInitialState(): SplashContract.State {
        return SplashContract.State()
    }

    override fun handleEvents(event: SplashContract.Event) {}
}