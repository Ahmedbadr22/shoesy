package com.ab.data.repository

import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toRequest
import com.ab.data.service.AuthenticationService
import com.ab.domain.model.data.Token
import com.ab.domain.model.form.LoginForm
import com.ab.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authenticationService: AuthenticationService
) : AuthenticationRepository {
    override suspend fun login(loginForm: LoginForm): Token {
        val loginRequest = loginForm.toRequest()
        val tokenDto = authenticationService.login(loginRequest)
        return tokenDto.toDomain()
    }
}