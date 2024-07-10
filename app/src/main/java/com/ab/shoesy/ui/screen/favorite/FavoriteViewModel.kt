package com.ab.shoesy.ui.screen.favorite

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.product.ListUserFavoriteShoesUseCases
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val listUserFavoriteShoesUseCases: ListUserFavoriteShoesUseCases,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
) : BaseViewModel<FavoriteContract.Event, FavoriteContract.State>() {


    init {
        viewModelScopeWithHandler.launch {
            listUserFavoriteShoesUseCases().collectLatest { resource ->
                resource.handle(
                    onLoading = { isLoading ->
                        setState { copy(loading = isLoading) }
                    },
                    onSuccess = { data ->
                        setState { copy(shoes = data) }
                    },
                    onError = { throwable ->
                        Log.i("AHMED_BADR", "Error Favorite View Model init : $throwable ")
                    }
                )
            }
        }
    }

    private fun markShoeAsUnFavorite(shoeId: Int) = viewModelScopeWithHandler.launch {
        markShoeAsUnFavoriteByIdUseCase(shoeId).collectLatest { resource ->
            resource.handle(
                onLoading = { isLoading -> setState { copy(loading = isLoading) } },
                onSuccess = {
                    val updatedShoes = viewState.value.shoes.dropWhile { shoe -> shoe.id == shoeId }
                    setState {
                        copy(
                            shoes = updatedShoes.toList()
                        )
                    }
                },
                onError = { throwable ->
                    Log.i("AHMED_BADR", "Error Favorite View Model markAsFavoriteShoe : $throwable ")
                }
            )
        }
    }

//    private fun markAsFavoriteShoe(shoeId: Int) = viewModelScopeWithHandler.launch {
//        markShoeAsFavoriteByIdUseCase(shoeId).collectLatest { resource ->
//            resource.handle(
//                onLoading = { isLoading -> setState { copy(loading = isLoading) } },
//                onSuccess = {
//                    val updatedShoes = viewState.value.shoes.map { shoe ->
//                        if (shoe.id == shoeId) shoe.copy(isFavorite = true)
//                        else shoe
//                    }
//                    setState {
//                        copy(
//                            shoes = updatedShoes.toList()
//                        )
//                    }
//                },
//                onError = { throwable ->
//                    Log.i("AHMED_BADR", "Error Favorite View Model markAsFavoriteShoe : $throwable ")
//                }
//            )
//        }
//    }

    override fun setInitialState(): FavoriteContract.State {
        return FavoriteContract.State()
    }

    override fun handleEvents(event: FavoriteContract.Event) {
        when(event) {
            is FavoriteContract.Event.MarkAsUnFavoriteShoe -> markShoeAsUnFavorite(event.shoeId)
        }
    }
}