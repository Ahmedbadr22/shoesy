package com.ab.core.utils

import androidx.annotation.StringRes


sealed class Resource<out T>() {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
//    data object Idle: Resource<Nothing>()

    fun isSuccessful(): Boolean = this is Success
    fun isFailed(): Boolean = this is Error
    fun isLoading(): Boolean = this is Loading


    fun isEmpty(): Boolean {
        if (this is Success) {
            return if (this.data == null) {
                true
            } else {
                this.data is List<*> && this.data.isEmpty()
            }
        }
        return true
    }
}

sealed class ValidationResource() {
    data object Valid : ValidationResource()
    data class NotValid(@StringRes val stringResIdMessage: Int) : ValidationResource()
}

fun <T>Resource<T>.handle(
    onLoading : (Boolean) -> Unit,
    onSuccess : (T) -> Unit,
    onError: (Throwable) -> Unit
) {
    when(this) {
        is Resource.Error -> {
            onLoading(false)
            onError(exception)
        }
        Resource.Loading -> onLoading(true)
        is Resource.Success -> {
            onLoading(false)
            onSuccess(data)
        }
    }
}


fun ValidationResource.handle(
    onValid : () -> Unit,
    onNotValid : (Int) -> Unit,
) {
    when(this) {
        is ValidationResource.Valid -> onValid()
        is ValidationResource.NotValid -> onNotValid(stringResIdMessage)
    }
}