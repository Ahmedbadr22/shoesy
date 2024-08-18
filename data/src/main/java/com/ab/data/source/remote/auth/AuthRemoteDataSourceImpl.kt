package com.ab.data.source.remote.auth

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.TokenDto
import com.ab.data.model.request.LoginRequest
import com.ab.data.service.AuthenticationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthRemoteDataSourceImpl(
    private val authenticationService: AuthenticationService
) : AuthDataSource {

    override suspend fun login(loginRequest: LoginRequest) : TokenDto = safeApiCall {
        withContext(Dispatchers.IO) {
            authenticationService.login(loginRequest)
        }
    }
}