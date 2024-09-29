package com.ab.domain.repository

import com.ab.domain.model.data.Token
import com.ab.domain.model.data.User
import com.ab.domain.model.form.LoginForm
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun login(loginForm: LoginForm) : Token

    suspend fun setToken(token: Token)
    suspend fun getAccessToken(): String
    suspend fun getUserDetail(): Flow<User?>
}