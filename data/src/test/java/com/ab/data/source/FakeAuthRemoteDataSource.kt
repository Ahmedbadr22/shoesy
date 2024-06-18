package com.ab.data.source

import com.ab.core.R
import com.ab.core.utils.NetworkRequestException
import com.ab.data.model.dto.TokenDto
import com.ab.data.model.request.LoginRequest
import com.ab.data.source.remote.auth.AuthDataSource

class FakeAuthRemoteDataSource(
    private val users: Map<LoginRequest, TokenDto> = mapOf(
        LoginRequest("ahmed@gmail.com", "123456789") to TokenDto("", ""),
        LoginRequest("ahmedbadr121@gmail.com", "12345678") to TokenDto("", ""),
    )
) : AuthDataSource {
    override suspend fun login(loginRequest: LoginRequest): TokenDto {
        val key = users.keys.find { key ->
            key.email == loginRequest.email && key.password == loginRequest.password
        }

        return users[key] ?: throw NetworkRequestException(R.string.user_not_found_please_try_again, code = 401)
    }
}