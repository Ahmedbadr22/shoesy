package com.ab.shoesy.ui.screen.main

import com.ab.core.base.BaseViewModel
import com.ab.domain.usecases.cart.GetCartItemCountUseCase
import com.ab.domain.usecases.product.GetFavoriteShoeCountUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val getFavoriteShoeCountUseCase: GetFavoriteShoeCountUseCase,
    private val getCartItemCountUseCase: GetCartItemCountUseCase
): BaseViewModel<MainContract.Event, MainContract.State>() {

    init {
        viewModelScopeWithHandler.launch {
            launch {
                getFavoriteItemsCount()
            }

            launch {
                getCartItemsCount()
            }
        }
    }

    private suspend fun getFavoriteItemsCount() {
        getFavoriteShoeCountUseCase().collectLatest { count ->
            setState { copy(favoriteItemsCount = count) }
        }
    }

    private suspend fun getCartItemsCount() {
        getCartItemCountUseCase().collectLatest { count ->
            setState { copy(cartItemsCount = count) }
        }
    }

    override fun setInitialState(): MainContract.State {
        return MainContract.State()
    }

    override fun handleEvents(event: MainContract.Event) {}
}