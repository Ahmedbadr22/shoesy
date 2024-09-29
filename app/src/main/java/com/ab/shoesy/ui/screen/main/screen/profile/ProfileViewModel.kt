package com.ab.shoesy.ui.screen.main.screen.profile

import com.ab.core.base.BaseViewModel
import com.ab.domain.usecases.auth.GetUserDetailUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase
): BaseViewModel<ProfileContract.Event, ProfileContract.State>() {

    init {
        viewModelScopeWithHandler.launch {
            launchCoroutine {
                getUserDetailUseCase().collectLatest { userDetail ->
                    setState { copy(userDetail = userDetail) }
                }
            }
        }
    }

    override fun setInitialState(): ProfileContract.State {
        return ProfileContract.State()
    }

    override fun handleEvents(event: ProfileContract.Event) {
        when(event) {
            else -> {}
        }
    }
}