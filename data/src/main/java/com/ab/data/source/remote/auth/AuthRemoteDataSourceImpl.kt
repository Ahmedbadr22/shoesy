package com.ab.data.source.remote.auth

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.TokenDto
import com.ab.data.model.request.LoginRequest
import com.ab.data.service.AuthenticationService


class AuthRemoteDataSourceImpl(
    private val authenticationService: AuthenticationService
) : AuthDataSource {

    override suspend fun login(loginRequest: LoginRequest) : TokenDto = safeApiCall {
        authenticationService.login(loginRequest)
    }
}