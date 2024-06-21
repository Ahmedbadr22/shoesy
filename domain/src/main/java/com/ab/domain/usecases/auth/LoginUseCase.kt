package com.ab.domain.usecases.auth

import android.util.Log
import com.ab.core.base.BaseSuspendIOUseCase
import com.ab.core.utils.Resource
import com.ab.domain.model.data.Token
import com.ab.domain.model.form.LoginForm
import com.ab.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val authenticationRepository: AuthenticationRepository
) : BaseSuspendIOUseCase<LoginForm, Flow<Resource<Token>>> {
    override suspend fun invoke(input: LoginForm): Flow<Resource<Token>> = flow {
        emit(Resource.Loading)
        val token = authenticationRepository.login(input)
        emit(Resource.Success(token))
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }
}