package com.ab.shoesy.ui.screen.favorite

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.repository.ProductRepository
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val productRepository: ProductRepository,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
) : BaseViewModel<FavoriteContract.Event, FavoriteContract.State>() {


    init {
        listUserFavoriteShoe()
    }

    private fun listUserFavoriteShoe() {
        viewModelScopeWithHandler.launch {
            productRepository.listAllFavoritesFlow()
                .collectLatest { data ->
                    setState { copy(shoes = data, loading = false) }
                }
        }
    }

    private fun markShoeAsUnFavorite(shoeId: Int, onSuccess: () -> Unit) =
        viewModelScopeWithHandler.launch {
            markShoeAsUnFavoriteByIdUseCase(shoeId).collectLatest { resource ->
                resource.handle(
                    onLoading = { },
                    onSuccess = { onSuccess() },
                    onError = { throwable ->
                        Log.i(
                            "AHMED_BADR",
                            "Error Favorite View Model markAsFavoriteShoe : $throwable "
                        )
                    }
                )
            }
        }

    override fun setInitialState(): FavoriteContract.State {
        return FavoriteContract.State()
    }

    override fun handleEvents(event: FavoriteContract.Event) {
        when (event) {
            is FavoriteContract.Event.MarkAsUnFavoriteShoe -> {
                markShoeAsUnFavorite(
                    event.shoeId,
                    onSuccess = ::listUserFavoriteShoe
                )
            }
        }
    }
}