package com.ab.domain.usecases.auth

import com.ab.core.base.BaseSuspendOUseCase
import com.ab.domain.model.data.User
import com.ab.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailUseCase(
    private val authenticationRepository: AuthenticationRepository
) : BaseSuspendOUseCase<Flow<User?>> {
    override suspend fun invoke(): Flow<User?> {
        return authenticationRepository.getUserDetail()
    }
}
