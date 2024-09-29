package com.ab.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import com.ab.data.datastore.getString
import com.ab.data.datastore.getStringFlow
import com.ab.data.datastore.setString
import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toRequest
import com.ab.data.source.remote.auth.AuthDataSource
import com.ab.domain.model.data.Token
import com.ab.domain.model.data.User
import com.ab.domain.model.form.LoginForm
import com.ab.domain.repository.AuthenticationRepository
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

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
        return context.authDataStore.getString("access_token") ?: ""
    }

    override suspend fun getUserDetail(): Flow<User?> {
        return context.authDataStore.getStringFlow("access_token", "").map { token ->
            if (token != null) {
                val jwt = JWT(token)
                User(
                    email = jwt.getClaim("email").asString().orEmpty(),
                    fullname = jwt.getClaim("fullname").asString().orEmpty(),
                    isMale = jwt.getClaim("is_male").asBoolean() ?: false,
                    profileImageUrl = jwt.getClaim("profile_image").asString().orEmpty()
                )
            } else {
                null
            }
        }
    }
}