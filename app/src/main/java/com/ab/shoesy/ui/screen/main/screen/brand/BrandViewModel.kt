package com.ab.shoesy.ui.screen.main.screen.brand

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.repository.BrandRepository
import com.ab.domain.usecases.product.ListShoesByBrandIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BrandViewModel(
    private val listShoesByBrandIdUseCase: ListShoesByBrandIdUseCase,
    private val markShoeAsFavoriteByIdUseCase: MarkShoeAsFavoriteByIdUseCase,
    private val markShoeAsUnFavoriteByIdUseCase: MarkShoeAsUnFavoriteByIdUseCase,
    private val brandRepository: BrandRepository
) : BaseViewModel<BrandContract.Event, BrandContract.State>() {
    override fun setInitialState(): BrandContract.State {
        return BrandContract.State()
    }

    init {
        listBrands()
    }

    override fun handleEvents(event: BrandContract.Event) {
        when (event) {
            is BrandContract.Event.ListShoesByBrandId -> listShoesByBrandId(event.brandId)
            is BrandContract.Event.MarkShoeAsFavorite -> markAsFavoriteShoe(event.shoeId)
            is BrandContract.Event.MarkShoeAsUnFavorite -> markAsNotFavoriteShoe(event.shoeId)
        }
    }

    private fun markAsFavoriteShoe(shoeId: Int) = viewModelScopeWithHandler.launch {
        markShoeAsFavoriteByIdUseCase(shoeId).collectLatest { resource ->
            resource.handle(
                onLoading = { _ ->  },
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
                onLoading = { _ ->  },
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


    private fun listShoesByBrandId(brandId: Int) {
        viewModelScopeWithHandler.launch {
            listShoesByBrandIdUseCase(brandId)
                .onStart { setState { copy(loading = true) } }
                .collectLatest { shoes -> setState { copy(shoes = shoes, loading = false) } }
        }
    }

    private fun listBrands() {
        viewModelScopeWithHandler.launch {
            brandRepository.brandsFlow.collectLatest { brands ->
                setState { copy(brands = brands) }
            }
        }
    }
}