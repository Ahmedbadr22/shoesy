package com.ab.domain.usecases.auth

import com.ab.core.base.BaseSuspendOUseCase
import com.ab.domain.repository.AuthenticationRepository

class IsAuthenticatedUserUseCase(
    private val authenticationRepository: AuthenticationRepository
) : BaseSuspendOUseCase<Boolean> {
    override suspend fun invoke(): Boolean {
        return authenticationRepository.getAccessToken().isNotEmpty()
    }
}
