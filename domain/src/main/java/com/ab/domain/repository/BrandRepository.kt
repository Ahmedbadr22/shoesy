package com.ab.domain.repository

import com.ab.domain.model.data.Brand
import kotlinx.coroutines.flow.Flow


interface BrandRepository {

    val brandsFlow: Flow<List<Brand>>
    suspend fun listFromRemoteAndInsertToLocal(token: String)
}