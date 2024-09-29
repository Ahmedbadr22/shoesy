package com.ab.shoesy.ui.screen.main.screen.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.repository.BrandRepository
import com.ab.domain.usecases.auth.GetUserDetailUseCase
import com.ab.domain.usecases.brand.ListBrandsFromRemoteToLocalUseCase
import com.ab.domain.usecases.cart.GetCartItemCountUseCase
import com.ab.domain.usecases.product.GetFavoriteShoeCountUseCase
import com.ab.domain.usecases.product.ListSpecialShoeForYouUseCase
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listBrandsFromRemoteToLocalUseCase: ListBrandsFromRemoteToLocalUseCase,
    private val listSpecialShoeForYouUseCase: ListSpecialShoeForYouUseCase,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
    private val markShoeAsFavoriteByIdUseCase: MarkShoeAsFavoriteByIdUseCase,
    private val brandRepository: BrandRepository,
    private val getFavoriteShoeCountUseCase: GetFavoriteShoeCountUseCase,
    private val getCartItemCountUseCase: GetCartItemCountUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.State>() {


    init {
        viewModelScopeWithHandler.launch {
            launchCoroutine {
                listBrandsFromRemoteToLocalUseCase()
            }

            launchCoroutine {
                brandRepository.brandsFlow
                    .onStart { setState { copy(loading = true) } }
                    .collectLatest { brands ->
                        setState { copy(brands = brands, loading = false) }
                    }
            }

            launchCoroutine {
                listSpecialShoeForYou()
            }
            launchCoroutine {
                handleFavoritesItemsCount()
            }

            launchCoroutine { handleCartItemsCount() }

            launchCoroutine {
                getUserDetailUseCase().collectLatest { userDetail ->
                    setState { copy(userDetail = userDetail) }
                }
            }
        }
    }

    private fun markAsFavoriteShoe(shoeId: Int) = viewModelScopeWithHandler.launch {
        markShoeAsFavoriteByIdUseCase(shoeId).collectLatest { resource ->
            resource.handle(
                onLoading = { },
                onSuccess = { },
                onError = { throwable ->
                    Log.i(
                        "AHMED_BADR",
                        "Error Favorite View Model markAsFavoriteShoe : $throwable "
                    )
                }
            )
        }
    }

    private fun markAsNotFavoriteShoe(shoeId: Int) = viewModelScopeWithHandler.launch {
        markShoeAsUnFavoriteByIdUseCase(shoeId).collectLatest { resource ->
            resource.handle(
                onLoading = { },
                onSuccess = { },
                onError = { throwable ->
                    Log.i(
                        "AHMED_BADR",
                        "Error Favorite View Model markAsNotFavoriteShoe : $throwable "
                    )
                }
            )
        }
    }

    override fun setInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.MarkSpecialForYouShoeAsFavorite -> markAsFavoriteShoe(event.shoeId)
            is HomeContract.Event.MarkSpecialForYouShoeAsUnFavorite -> markAsNotFavoriteShoe(event.shoeId)
        }
    }

    private suspend fun listSpecialShoeForYou() {
        listSpecialShoeForYouUseCase()
            .onStart { setState { copy(specialForYouLoading = true) } }
            .collectLatest { shoeList ->
            setState { copy(specialForYouShoes = shoeList, specialForYouLoading = false) }
        }
    }

    private fun handleFavoritesItemsCount() {
        viewModelScope.launch {
            getFavoriteShoeCountUseCase().collectLatest { count ->
                setState { copy(favoriteItemsCount = count) }
            }
        }
    }

    private fun handleCartItemsCount() {
        viewModelScope.launch {
            getCartItemCountUseCase().collectLatest { count ->
                setState { copy(cartItemsCount = count) }
            }
        }
    }
}