package com.ab.data.service

import com.ab.data.model.dto.TokenDto
import com.ab.data.model.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST
    suspend fun login(@Body loginRequest: LoginRequest) : TokenDto
}