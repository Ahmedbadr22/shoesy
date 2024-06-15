package com.ab.domain.repository

import com.ab.domain.model.data.Token
import com.ab.domain.model.form.LoginForm

interface AuthenticationRepository {

    suspend fun login(loginForm: LoginForm) : Token
}