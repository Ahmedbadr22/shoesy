package com.ab.shoesy.ui.screen.shoe

import com.ab.core.base.BaseViewModel
import com.ab.core.utils.handle
import com.ab.domain.usecases.product.GetShoeByIdUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShoeViewModel(
    private val getShoeByIdUseCase: GetShoeByIdUseCase
): BaseViewModel<ShoeContract.Event, ShoeContract.State>() {
    override fun setInitialState(): ShoeContract.State {
        return ShoeContract.State()
    }

    override fun handleEvents(event: ShoeContract.Event) {
        when(event) {
            is ShoeContract.Event.GetShoeById -> getShoeById(event.id)
        }
    }

    private fun getShoeById(id: Int) {
        viewModelScopeWithHandler.launch {
            getShoeByIdUseCase(id).collectLatest { resource ->
                resource.handle(
                    onLoading = { isLoading -> setState { copy(loading = isLoading) } },
                    onSuccess = { data -> setState { copy(shoe = data) }},
                    onError = { error -> }
                )
            }
        }
    }
}