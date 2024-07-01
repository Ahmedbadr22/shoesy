package com.ab.domain.usecases.auth

import com.ab.core.base.BaseSuspendOUseCase
import com.ab.domain.repository.AuthenticationRepository

class GetAccessTokenUseCase(
    private val authenticationRepository: AuthenticationRepository
) : BaseSuspendOUseCase<String> {
    override suspend fun invoke(): String {
        return "Bearer ${authenticationRepository.getAccessToken()}"
    }
}
