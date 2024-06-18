package com.ab.data.repository

import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toRequest
import com.ab.data.source.remote.auth.AuthDataSource
import com.ab.data.source.remote.auth.AuthRemoteDataSourceImpl
import com.ab.domain.model.data.Token
import com.ab.domain.model.form.LoginForm
import com.ab.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authRemoteDataSource: AuthDataSource
) : AuthenticationRepository {
    override suspend fun login(loginForm: LoginForm): Token {
        val loginRequest = loginForm.toRequest()
        val tokenDto = authRemoteDataSource.login(loginRequest)
        return tokenDto.toDomain()
    }
}