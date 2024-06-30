package com.ab.shoesy.ui.screen.brand

import android.util.Log
import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.product.ListShoesByBrandIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BrandViewModel(
    private val listShoesByBrandIdUseCase: ListShoesByBrandIdUseCase
) : BaseViewModel<BrandContract.Event, BrandContract.State>() {
    override fun setInitialState(): BrandContract.State {
        return BrandContract.State()
    }

    override fun handleEvents(event: BrandContract.Event) {
        when(event) {
            is BrandContract.Event.ListShoesByBrandId -> listShoesByBrandId(event.brandId)
        }
    }


    private fun listShoesByBrandId(brandId: Int) {
        viewModelScopeWithHandler.launch {
            listShoesByBrandIdUseCase(brandId).collectLatest { resource ->
                resource.handle(
                    onLoading = { isLoading -> setState { copy(loading = isLoading) } },
                    onSuccess = { data -> setState { copy(shoes = data) } },
                    onError = {
                        Log.i("AHMED_BADR", "listShoesByBrandId: Error $it")
                    }
                )
            }
        }
    }
}