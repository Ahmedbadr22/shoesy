package com.ab.domain.usecases.utils

import android.util.Patterns.EMAIL_ADDRESS
import com.ab.core.base.BaseIOUseCase
import com.ab.core.utils.ValidationResource
import com.ab.domain.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EmailValidationUseCase : BaseIOUseCase<String, Flow<ValidationResource>> {
    override fun invoke(input: String): Flow<ValidationResource> = flow {
        if (input.isEmpty()) {
            emit(ValidationResource.NotValid(R.string.can_t_be_empty))
            return@flow
        }

        if (!EMAIL_ADDRESS.matcher(input).matches()) {
            emit(ValidationResource.NotValid(R.string.not_valid_email_address))
            return@flow
        }

        emit(ValidationResource.Valid)
    }
}