package com.ab.data.repository

import com.ab.core.utils.NetworkRequestException
import com.ab.data.source.FakeAuthRemoteDataSource
import com.ab.domain.model.form.LoginForm
import com.ab.domain.repository.AuthenticationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AuthRepositoryTest  {
//
//    private lateinit var authRemoteDataSource: FakeAuthRemoteDataSource
//
//    private lateinit var authenticationRepository: AuthenticationRepository
//
//    @Before
//    fun createRepository() {
//        authRemoteDataSource = FakeAuthRemoteDataSource()
//
//        authenticationRepository = AuthenticationRepositoryImpl(authRemoteDataSource, )
//    }
//
//    @Test
//    fun getUserTokens_emptyEmailAndPassword_returnTokens() = runBlocking {
//        val loginForm = LoginForm("ahmedbadr121@gmail.com", "12345678")
//
//        val token = authenticationRepository.login(loginForm)
//        assert(token.access.isEmpty() && token.refresh.isEmpty())
//    }
//
//    @Test
//    fun getUserTokens_wrongEmailAndPassword_raiseException() = runBlocking {
//        val loginForm = LoginForm("ahmedbadr1@gmail.com", "1234578")
//        try {
//            val token = authenticationRepository.login(loginForm)
//        } catch (exception:  NetworkRequestException) {
//            assert(exception.code == 401)
//        }
//
//    }
}