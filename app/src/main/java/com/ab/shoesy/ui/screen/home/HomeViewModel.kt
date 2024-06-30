package com.ab.shoesy.ui.screen.home

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.brand.ListBrandsUseCase
import com.ab.domain.usecases.product.ListSpecialShoeForYouUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listBrandsUseCase: ListBrandsUseCase,
    private val listSpecialShoeForYouUseCase: ListSpecialShoeForYouUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.State>() {


    init {
        viewModelScopeWithHandler.launch {
            launch {
                listBrands()
            }

            launch {
                listSpecialShoeForYou()
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

    private suspend fun listSpecialShoeForYou() {
        listSpecialShoeForYouUseCase().collectLatest { resource ->
            resource.handle(
                onLoading = {},
                onSuccess = { stockList ->
                    setState { copy(specialForYouShoes = stockList) }
                },
                onError = {
                    Log.i("AHMED_BADR", "listSpecialShoeForYou: Size Error $it")
                }
            )
        }
    }

    private fun onLoadingStateChange(isLoading: Boolean) {
        setState { copy(loading = isLoading) }
    }
}