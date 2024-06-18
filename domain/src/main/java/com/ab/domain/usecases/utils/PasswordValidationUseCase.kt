package com.ab.domain.usecases.utils

import com.ab.core.base.BaseIOUseCase
import com.ab.core.utils.ValidationResource
import com.ab.domain.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PasswordValidationUseCase : BaseIOUseCase<String, Flow<ValidationResource>> {
    override fun invoke(input: String): Flow<ValidationResource> = flow {
        if (input.isEmpty()) {
            emit(ValidationResource.NotValid(R.string.can_t_be_empty))
            return@flow
        }

        if (input.length < 8) {
            emit(ValidationResource.NotValid(R.string.password_can_t_be_less_than_8_char_s))
            return@flow
        }

        emit(ValidationResource.Valid)
    }
}