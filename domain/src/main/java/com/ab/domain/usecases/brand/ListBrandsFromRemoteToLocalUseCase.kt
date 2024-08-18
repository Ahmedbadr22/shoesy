package com.ab.domain.usecases.brand

import com.ab.core.base.BaseSuspendUseCase
import com.ab.domain.repository.BrandRepository
import com.ab.domain.usecases.auth.GetAccessTokenUseCase

class ListBrandsFromRemoteToLocalUseCase(
    private val brandRepository: BrandRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : BaseSuspendUseCase {
    override suspend fun invoke() {
        val token = getAccessTokenUseCase()
        brandRepository.listFromRemoteAndInsertToLocal(token)
    }
}