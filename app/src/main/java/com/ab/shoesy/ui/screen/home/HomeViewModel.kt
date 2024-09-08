package com.ab.shoesy.ui.screen.home

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.repository.BrandRepository
import com.ab.domain.usecases.brand.ListBrandsFromRemoteToLocalUseCase
import com.ab.domain.usecases.product.ListSpecialShoeForYouUseCase
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listBrandsFromRemoteToLocalUseCase: ListBrandsFromRemoteToLocalUseCase,
    private val listSpecialShoeForYouUseCase: ListSpecialShoeForYouUseCase,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
    private val markShoeAsFavoriteByIdUseCase: MarkShoeAsFavoriteByIdUseCase,
    private val brandRepository: BrandRepository
) : BaseViewModel<HomeContract.Event, HomeContract.State>() {


    init {
        viewModelScopeWithHandler.launch {
            launch {
                listBrandsFromRemoteToLocalUseCase()
            }

            launch {
                brandRepository.brandsFlow
                    .onStart { setState { copy(loading = true) } }
                    .collectLatest { brands ->
                        setState { copy(brands = brands, loading = false) }
                    }
            }

            launch {
                listSpecialShoeForYou()
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
}