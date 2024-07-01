package com.ab.data.repository

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.ab.data.datastore.getStringFlow
import com.ab.data.datastore.setString
import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toRequest
import com.ab.data.source.remote.auth.AuthDataSource
import com.ab.domain.model.data.Token
import com.ab.domain.model.form.LoginForm
import com.ab.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authRemoteDataSource: AuthDataSource,
    private val context: Context
) : AuthenticationRepository {
    private val Context.authDataStore by preferencesDataStore(name = "auth")

    override suspend fun login(loginForm: LoginForm): Token {
        val loginRequest = loginForm.toRequest()
        val tokenDto = authRemoteDataSource.login(loginRequest)
        val token = tokenDto.toDomain()
        setToken(token)
        return token
    }

    override suspend fun setToken(token: Token) {
        context.authDataStore.apply {
            setString("access_token", token.access)
            setString("refresh_token", token.refresh)
        }
    }

    override suspend fun getAccessToken(): String {
        return context.authDataStore.getStringFlow("access_token") ?: "null"
    }
}