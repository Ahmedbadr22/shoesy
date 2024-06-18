package com.ab.data.source.remote.auth

import com.ab.data.model.dto.TokenDto
import com.ab.data.model.request.LoginRequest

interface AuthDataSource {

    suspend fun login(loginRequest: LoginRequest) : TokenDto
}