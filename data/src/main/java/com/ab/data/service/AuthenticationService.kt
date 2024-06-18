package com.ab.data.service

import com.ab.core.constants.EndPoint
import com.ab.data.model.dto.TokenDto
import com.ab.data.model.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST(EndPoint.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: LoginRequest) : Response<TokenDto>
}