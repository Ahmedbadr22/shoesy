package com.ab.shoesy.ui.screen.home

import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.brand.ListBrandsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listBrandsUseCase: ListBrandsUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.State>() {


    init {
        viewModelScopeWithHandler.launch {
            launch {
                listBrands()
            }
        }
    }

    override fun setInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            else -> {}
        }
    }

    private suspend fun listBrands() {
        listBrandsUseCase().collectLatest { resource ->
            resource.handle(
                onLoading = ::onLoadingStateChange,
                onSuccess = { brands ->
                    setState { copy(brands = brands) }
                },
                onError = {
                }
            )
        }
    }

    private fun onLoadingStateChange(isLoading: Boolean) {
        setState { copy(loading = isLoading) }
    }
}